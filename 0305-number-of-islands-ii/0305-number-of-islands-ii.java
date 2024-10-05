class Solution {
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind();
        List<Integer> ans = new ArrayList<>();
        int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < positions.length; i++)  {
            int x = positions[i][0];
            int y = positions[i][1];
            int idx = posToIdx(m, n, x, y);
            uf.add(idx);
            for (int[] d : dir) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
                int nIdx = posToIdx(m, n, nx, ny);
                uf.union(idx, nIdx);
            }
            ans.add(uf.getCnt());
        }
        
        return ans;
    }
    
    private int posToIdx(int m, int n, int x, int y)    {
        return x * n + y;
    }
}

class UnionFind {
    private Map<Integer, Integer> parents;
    private Map<Integer, Integer> size;
    private int cnt;
    
    public UnionFind() {
        cnt = 0;
        parents = new HashMap<>();
        size = new HashMap<>();
    }
    
    public int getCnt() {
        return cnt;
    }
    
    
    private int find(int x) {
        if (parents.get(x) != x)
            parents.put(x, find(parents.get(x)));
        
        return parents.get(x);
    }
    
    public void add(int x)  {
        if (parents.containsKey(x))
            return;
        parents.put(x, x);
        size.put(x, 1);
        cnt++;
    }
    
    
    public void union(int x, int y)    {
        if (!parents.containsKey(x) || !parents.containsKey(y))
            return;
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        
        if (size.get(rootX) > size.get(rootY))  {
            int t = rootX;
            rootX = rootY;
            rootY = t;
        }
        
        
        parents.put(rootX, rootY);
        size.put(rootY, size.get(rootY) + size.get(rootX));
        cnt--;
    }
}