class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        int[] edgeCnt = new int[n];
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            uf.union(u, v);
            edgeCnt[u]++;
            edgeCnt[v]++;
        }
        
        Map<Integer, List<Integer>> union = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            if (!union.containsKey(root))
                union.put(root, new LinkedList<>());
            union.get(root).add(i);
        }
        
        int ans = 0;
        for (Map.Entry<Integer, List<Integer>> e : union.entrySet())   {
            int k = e.getKey();
            List<Integer> v = e.getValue();
            boolean complete = true;
            for (int i : v) {
                if (edgeCnt[i] != uf.cnt[k] - 1){
                    complete = false;
                    break;
                }
            }
            if (complete)
                ans++;
        }
        
        return ans;
    }
}

class UnionFind {
    int[] parents;
    int[] cnt;
    
    public UnionFind(int n)    {
        parents = new int[n];
        cnt = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            cnt[i] = 1;
        }
    }
    
    int find(int node)  {
        if (parents[node] != node)  {
            parents[node] = find(parents[node]);
        }
        return parents[node];
    }
    
    void union(int p, int q)    {
        int pRoot = find(p);
        int qRoot = find(q);
        
        if (pRoot == qRoot)
            return;
        
        if (cnt[pRoot] > cnt[qRoot])  {
            int t = pRoot;
            pRoot = qRoot;
            qRoot = t;
        }
        
        parents[pRoot] = qRoot;
        cnt[qRoot] += cnt[pRoot];
    }
}