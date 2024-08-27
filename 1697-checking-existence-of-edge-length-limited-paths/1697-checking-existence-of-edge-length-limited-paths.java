class Solution {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        UnionFind uf = new UnionFind(n);
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < queries.length; i++)
            idx.add(i);
        Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
        Collections.sort(idx, (a, b) -> queries[a][2] - queries[b][2]);
        boolean[] ans = new boolean[queries.length];
        int j = 0;
        for (int i : idx)   {
            while (j < edgeList.length && edgeList[j][2] < queries[i][2])   {
                uf.union(edgeList[j][0], edgeList[j][1]);
                j++;
            }
            ans[i] = uf.isConnect(queries[i][0], queries[i][1]);
        }
        
        return ans;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    int[] size;
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
        rank = new int[n];
        Arrays.fill(rank, 1);
        size = new int[n];
        Arrays.fill(size, 1);
    }
    
    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    public boolean isConnect(int x, int y)  {
        int xRoot = find(x);
        int yRoot = find(y);
        return xRoot == yRoot;
    }
    
    public void union(int x, int y)  {
        int xRoot = find(x);
        int yRoot = find(y);
        
        if (xRoot == yRoot)
            return;
        if (size[xRoot] < size[yRoot])  {
            int t = xRoot;
            xRoot = yRoot;
            yRoot = t;
        }
        parent[yRoot] = xRoot;
        rank[xRoot]++;
        size[xRoot] += size[yRoot];
    }
}