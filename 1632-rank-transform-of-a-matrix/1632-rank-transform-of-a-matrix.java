class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        Map<Integer, List<Integer>> g = new HashMap<>();
        Map<Integer, Integer> indeg = new HashMap<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                indeg.put(i * n + j, 0);
        UnionFind uf = new UnionFind(n * m);
        for (int i = 0; i < m; i++) {
            int[][] temp = new int[n][2];
            for (int j = 0; j < n; j++)
                temp[j] = new int[] {matrix[i][j], i * n + j};
            Arrays.sort(temp, (a, b) -> a[0] - b[0]);
            for (int j = 1; j < n; j++) {
                if (temp[j - 1][0] < temp[j][0])    {
                    if (!g.containsKey(temp[j - 1][1]))
                        g.put(temp[j - 1][1], new LinkedList<>());
                    g.get(temp[j - 1][1]).add(temp[j][1]);
                    indeg.put(temp[j][1], indeg.get(temp[j][1]) + 1);
                }
                else    {
                    uf.union(temp[j - 1][1], temp[j][1]);
                }
            }
        }
        
        for (int j = 0; j < n; j++) {
            int[][] temp = new int[m][2];
            for (int i = 0; i < m; i++)
                temp[i] = new int[] {matrix[i][j], i * n + j};
            Arrays.sort(temp, (a, b) -> a[0] - b[0]);
            for (int i = 1; i < m; i++) {
                if (temp[i - 1][0] < temp[i][0])    {
                    if (!g.containsKey(temp[i - 1][1]))
                        g.put(temp[i - 1][1], new LinkedList<>());
                    g.get(temp[i - 1][1]).add(temp[i][1]);
                    indeg.put(temp[i][1], indeg.get(temp[i][1]) + 1);
                }
                else    {
                    uf.union(temp[i - 1][1], temp[i][1]);
                }
            }
        }
        
        Map<Integer, List<Integer>> grp = new HashMap<>();
        for (int i = 0; i < m * n; i++) {
            int root = uf.find(i);
            if (!grp.containsKey(root))
                grp.put(root, new LinkedList<>());
            grp.get(root).add(i);
            if (root != i)
                indeg.put(root, indeg.get(root) + indeg.get(i));
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int i : grp.keySet()) {
            if (indeg.get(i) == 0)  {
                q.offer(i);
            }
        }
        int r = 1;
        int[][] ans = new int[m][n];
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                for (int x : grp.get(u)) {
                    ans[x / n][x % n] = r;
                }
                for (int x : grp.get(u)) {
                    if (!g.containsKey(x))
                        continue;
                    for (int v : g.get(x))  {
                        int vRoot = uf.find(v);
                        indeg.put(vRoot, indeg.get(vRoot) - 1);
                        if (indeg.get(vRoot) == 0)
                            q.offer(vRoot);
                    }
                }
            }
            r++;
        }
        return ans;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    
    int find(int x) {
        if (parent[x] != x)   {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    
    void union(int x, int y)    {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        if (rank[rootX] < rank[rootY])  {
            int t = rootX;
            rootX = rootY;
            rootY = t;
        }
        
        parent[rootY] = rootX;
        rank[rootX]++;
    }
}