class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> nodes = new HashSet<>();
        for (List<String> equation : equations)
            for (String node : equation)
                nodes.add(node);
        UnionFind uf = new UnionFind(nodes);
        for (int i = 0; i < values.length; i++) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            double w = values[i];
            uf.union(x, y, w);
        }
        
        
        double[] ans = new double[queries.size()];
        
        for (int i = 0; i < queries.size(); i++)    {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            ans[i] = !uf.isConnected(x, y) ? -1 : uf.query(x, y);
        }
        
        return ans;
    }
}

class UnionFind {
    Map<String, String> parents;
    Map<String, Double> weights;
    
    public UnionFind(Set<String> nodes)  {
        parents = new HashMap<>();
        weights = new HashMap<>();
        for (String node : nodes)   {
            parents.put(node, node);
            weights.put(node, 1.0);
        }
    }
    
    public double query(String x, String y)    {
        return weights.get(x) / weights.get(y);
    }
    
    Pair<String, Double> find(String x)   {
        if (!parents.get(x).equals(x)) {
            Pair<String, Double> res = find(parents.get(x));
            weights.put(x, weights.get(x) * res.getValue());
            parents.put(x, res.getKey());
        }
        return new Pair<String, Double>(parents.get(x), weights.get(x));
    }
    
    public boolean isConnected(String x, String y)  {
        return parents.containsKey(x) && parents.containsKey(y) && find(x).getKey().equals(find(y).getKey());
    }
    
    // x / y = value
    public void union(String x, String y, double value)    {
        Pair<String, Double> rootXPair = find(x);
        Pair<String, Double> rootYPair = find(y);
        String rootX = rootXPair.getKey();
        String rootY = rootYPair.getKey();
        if (rootX.equals(rootY))
            return;
        double rootXWeight = rootXPair.getValue();
        double rootYWeight = rootYPair.getValue();
        
        parents.put(rootX, rootY);
        weights.put(rootX, value * rootYWeight / rootXWeight);
    }
    
}