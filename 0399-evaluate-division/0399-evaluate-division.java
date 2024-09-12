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
    
    public String find(String x)  {
        if (!parents.get(x).equals(x))    {
            String root = find(parents.get(x));
            weights.put(x, weights.get(x) * weights.get(parents.get(x)));
            parents.put(x, root);
            
        }
        return parents.get(x);
    }
    
    public boolean isConnected(String x, String y)  {
        return parents.containsKey(x) && parents.containsKey(y) && find(x).equals(find(y));
    }
    
    // x / y = value
    public void union(String x, String y, double value)    {
        String rootX = find(x);
        String rootY = find(y);
        if (rootX.equals(rootY))
            return;
        
        parents.put(rootX, rootY);
        weights.put(rootX, value * weights.get(y) / weights.get(x));
    }
    
}