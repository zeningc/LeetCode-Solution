class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = queries.size();
        double[] ans = new double[n];
        UnionFind uf = new UnionFind(equations);
        for (int i = 0; i < values.length; i++) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            uf.union(x, y, values[i]);
        }
        
        for (int i = 0; i < n; i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            ans[i] = uf.connected(x, y) ? uf.query(x, y) : -1.0;
        }
        
        return ans;
    }
}

class UnionFind {
    Map<String, String> parent;
    Map<String, Double> weight;
    
    public UnionFind(List<List<String>> equations) {
        parent = new HashMap<>();
        weight = new HashMap<>();
        for (List<String> equ : equations)  {
            String x = equ.get(0);
            String y = equ.get(1);
            parent.put(x, x);
            parent.put(y, y);
            weight.put(x, 1.0);
            weight.put(y, 1.0);
        }
    }
    
    Pair<String, Double> find(String x)   {
        if (!parent.get(x).equals(x)) {
            Pair<String, Double> res = find(parent.get(x));
            weight.put(x, weight.get(x) * res.getValue());
            parent.put(x, res.getKey());
        }
        return new Pair<String, Double>(parent.get(x), weight.get(x));
    }
    
    boolean connected(String x, String y) {
        return parent.containsKey(x) && parent.containsKey(y) && find(x).getKey().equals(find(y).getKey());
    }
    
    void union(String x, String y, double value) {
        Pair<String, Double> a = find(x);
        Pair<String, Double> b = find(y);
        String rootX = a.getKey();
        String rootY = b.getKey();
        
        if (rootX.equals(rootY))
            return;
        
        parent.put(rootX, rootY);
        weight.put(rootX, value * b.getValue() / a.getValue());
    }
    
    double query(String x, String y)  {
        return weight.get(x) / weight.get(y);
    }
    
}