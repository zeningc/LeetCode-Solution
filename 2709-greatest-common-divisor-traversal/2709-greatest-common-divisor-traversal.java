class Solution {
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        int maxVal = 0;
        for (int i = 0; i < n; i++)
            maxVal = Math.max(maxVal, nums[i]);
        
        UnionFind uf = new UnionFind(maxVal);
        boolean[] vis = new boolean[maxVal+1];
        for (int i = 0; i < n; i++) {
            if (vis[nums[i]])
                continue;
            int num = nums[i];
            if (num == 1)
                return n == 1;
            for (int f = 2; f <= Math.sqrt(nums[i]); f++)   {
                if (num % f != 0)
                    continue;
                uf.union(f, nums[i]);
                while (num % f == 0)    {
                    num /= f;
                }
            }
            if (num > 1)
                uf.union(num, nums[i]);
            vis[nums[i]] = true;
        }
        for (int i = 1; i < n; i++) {
            if (!uf.connected(nums[i - 1], nums[i]))
                return false;
        }
        return true;
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
    
    boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}