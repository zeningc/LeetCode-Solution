class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        UnionFind uf = new UnionFind(n + 1);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] dislike : dislikes)  {
            int u = dislike[0];
            int v = dislike[1];
            if (!map.containsKey(u))
                map.put(u, new LinkedList<>());
            map.get(u).add(v);
            if (!map.containsKey(v))
                map.put(v, new LinkedList<>());
            map.get(v).add(u);
        }
        
        for (int key : map.keySet())    {
            List<Integer> val = map.get(key);
            int len = val.size();
            for (int i = 1; i < len; i++) {
                uf.union(val.get(i), val.get(i - 1));
            }
        }
        
        for (int[] dislike : dislikes)  {
            int u = dislike[0];
            int v = dislike[1];
            if (uf.connected(u, v))
                return false;
        }
        
        return true;
    }
}
class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int n)  {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    void union(int x, int y)    {
        x = find(x);
        y = find(y);
        if (x == y)
            return;
        if (rank[x] < rank[y])  {
            int t = x;
            x = y;
            y = t;
        }
        
        parent[y] = x;
        rank[x]++;
    }
}