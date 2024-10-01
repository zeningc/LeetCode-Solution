class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> strToId = new HashMap<>();
        int id = 0;
        for (List<String> equation : equations) {
            if (!strToId.containsKey(equation.get(0)));
                strToId.put(equation.get(0), id++);
            if (!strToId.containsKey(equation.get(1)));
                strToId.put(equation.get(1), id++);
        }
        
        UnionFind uf = new UnionFind(id);
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            List<String> equation = equations.get(i);
            String x = equation.get(0);
            String y = equation.get(1);
            
            uf.union(strToId.get(x), strToId.get(y), values[i]);
        }
        
        
        int m = queries.size();
        double[] ans = new double[m];
        for (int i = 0; i < m; i++) {
            List<String> query = queries.get(i);
            String x = query.get(0);
            String y = query.get(1);
            if (!strToId.containsKey(x) || !strToId.containsKey(y)) {
                ans[i] = -1.0;
                continue;
            }
            
            ans[i] = uf.query(strToId.get(x), strToId.get(y));
        }
        
        return ans;
    }
}

class UnionFind {
    
    int[] parents;
    double[] weights;
    
    
    public UnionFind(int n) {
        parents = new int[n];
        weights = new double[n];
        
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            weights[i] = 1.0;
        }
    }
    
    int find(int x) {
        if (parents[x] != x)    {
            int t = parents[x];
            parents[x] = find(parents[x]);
            weights[x] *= weights[t];
        }
        
        return parents[x];
    }
    
    boolean isConnected(int x, int y)   {
        return find(x) == find(y);
    }
    
    
    void union(int x, int y, double ratio)    {
        int xRoot = find(x);
        int yRoot = find(y);
        
        if (xRoot == yRoot)
            return;
        
        parents[xRoot] = parents[yRoot];
        weights[xRoot] = ratio * weights[y] / weights[x];
    }
    
    double query(int x, int y)  {
        if (!isConnected(x, y))
            return -1.0;
        
        return weights[x] / weights[y];
    }
}