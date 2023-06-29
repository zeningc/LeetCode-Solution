class Solution {
    public boolean gcdSort(int[] nums) {
        int maxVal = Arrays.stream(nums).boxed().max(Integer::compare).get();
        UnionFind uf = new UnionFind(maxVal);
        
        for (int num : nums)    {
            int t = num;
            for (int f = 2; f <= (int)Math.sqrt(num); f++)  {
                if (t % f != 0)
                    continue;
                uf.union(num, f);
                while (t % f == 0)    {
                    t /= f;
                }
            }
            if (t > 1)
                uf.union(t, num);
        }
        
        int[] copy = nums.clone();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++)   {
            if (nums[i] != copy[i] && !uf.connected(nums[i], copy[i]))
                return false;
        }
        
        return true;
    }
}

class UnionFind {
    int[] parents;
    int[] size;
    
    public UnionFind(int n)    {
        parents = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
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
        
        if (size[xRoot] > size[yRoot])  {
            int t = xRoot;
            xRoot = yRoot;
            yRoot = t;
        }
        
        parents[xRoot] = yRoot;
        size[yRoot] += size[xRoot];
        return;
    }
    
    boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}