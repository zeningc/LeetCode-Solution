class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        UnionFind uf = new UnionFind(m * n);
        int[][] dir = new int[][] {
            {0, 1}, {0, -1},
            {1, 0}, {-1, 0}
        };
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0'){
                    cnt++;
                    continue;
                }
                for (int[] d : dir)   {
                    int nx = i + d[0];
                    int ny = j + d[1];
                    if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1 || grid[nx][ny] == '0')
                        continue;
                    uf.connect(i * n + j, nx * n + ny);
                }
            }
        }
        
        return uf.cnt - cnt;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    int cnt;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        cnt = n;
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
    
    void connect(int x, int y)  {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        if (rank[rootY] > rank[rootX])  {
            int t = rootX;
            rootX = rootY;
            rootY = t;
        }
        
        parent[rootY] = rootX;
        rank[rootX]++;
        cnt--;
    }
}