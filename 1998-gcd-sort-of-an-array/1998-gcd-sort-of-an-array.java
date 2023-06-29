class Solution {
    public boolean gcdSort(int[] nums) {
        int maxVal = Arrays.stream(nums).boxed().max(Integer::compare).get();
        UnionFind uf = new UnionFind(maxVal);
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        for (int factor = 2; factor <= maxVal; factor++)  {
            int prev = -1;
            for (int j = 1; j * factor <= maxVal; j++)   {
                int cur = j * factor;
                if (set.contains(prev) && set.contains(cur))
                    uf.union(prev, cur);
                if (set.contains(cur))
                    prev = cur;
            }
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
    
    public UnionFind(int maxVal)    {
        parents = new int[maxVal + 1];
        size = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
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