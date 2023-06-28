class Solution {
    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        int nQuery = queries.length;
        List<Boolean> ans = new ArrayList<>(nQuery);
        
        for (int i = threshold + 1; i <= n; i++)    {
            int prev = i;
            for (int j = 2; j * i <= n; j++)    {
                uf.union(prev, i * j);
                prev = i * j;
            }
        }
        
        for (int[] query : queries) {
            int x = query[0];
            int y = query[1];
            if (uf.find(x) == uf.find(y))
                ans.add(true);
            else
                ans.add(false);
        }
        
        return ans;
    }
    
    Map<Integer, Integer> getPrimeFactor(int x) {
        int limit = (int)Math.sqrt(x);
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 2; i <= limit; i++)    {
            if (x % i != 0)
                continue;
            while (x % i == 0)  {
                freqMap.put(i, freqMap.getOrDefault(i, 0) + 1);
                x /= i;
            }
        }
        if (x > 1)
            freqMap.put(x, freqMap.getOrDefault(x, 0) + 1);
        return freqMap;
    }
}

class UnionFind {
    int[] parents;
    int[] size;
    
    public UnionFind(int n) {
        parents = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            parents[i] = i;
            size[i] = 1;
        }
    }
    
    int find(int x) {
        if (parents[x] != x)    {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
    
    void union(int x, int y)    {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot)
            return;
        
        if (size[xRoot] < size[yRoot])  {
            int t = xRoot;
            xRoot = yRoot;
            yRoot = t;
        }
        
        parents[xRoot] = yRoot;
        size[yRoot] += size[xRoot];
    }
}