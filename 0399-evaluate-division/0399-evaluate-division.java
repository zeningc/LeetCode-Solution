class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ans = new double[queries.size()];
        Set<String> source = new HashSet<>();
        
        for (List<String> equation : equations)
            for (String e : equation)
                source.add(e);
        
        UnionFind uf = new UnionFind(source);
        
        for (int i = 0; i < equations.size(); i++)  {
            List<String> equation = equations.get(i);
            uf.union(equation.get(0), equation.get(1), values[i]);
        }
        
        for (int i = 0; i < queries.size(); i++)    {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (!source.contains(x) || !source.contains(y) || !uf.isConnected(x, y)) {
                ans[i] = -1.0;
                continue;
            }
            
            ans[i] = uf.query(x, y);
        }
        
        return ans;
    }
}

class UnionFind {
    Map<String, String> parents;
    Map<String, Double> weights;
    
    public UnionFind(Set<String> set)  {
        parents = new HashMap<>();
        weights = new HashMap<>();
        for (String s : set)    {
            parents.put(s, s);
            weights.put(s, 1.0);
        }
    }
    
    private String find(String x)   {
        if (!parents.get(x).equals(x))    {
            String directParent = parents.get(x);
            parents.put(x, find(directParent));
            weights.put(x, weights.get(x) * weights.get(directParent));
        }
        
        return parents.get(x);
    }
    
    public boolean isConnected(String x, String y)  {
        return find(x).equals(find(y));
    }
    
    public double query(String x, String y)    {
        if (!isConnected(x, y))
            return -1.0;
        
        return weights.get(x) / weights.get(y);
    }
    
    public void union(String x, String y, double ratio)    {
        if (find(x).equals(find(y)))
            return;
        String xRoot = find(x);
        String yRoot = find(y);
        
        parents.put(xRoot, yRoot);
        weights.put(xRoot, ratio * weights.get(y) / weights.get(x));
    }
}