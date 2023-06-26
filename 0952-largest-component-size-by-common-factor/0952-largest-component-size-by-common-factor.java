class Solution {
    public int largestComponentSize(int[] nums) {
        Map<Integer, Set<Integer>> primeFactorMap = new HashMap<>();
        int n = nums.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int factor = 2;
            int limit = (int)Math.sqrt(num);
            while (factor <= limit) {
                if (num % factor != 0)  {
                    factor++;
                    continue;
                }
                if (!primeFactorMap.containsKey(factor))
                    primeFactorMap.put(factor, new HashSet<>());
                primeFactorMap.get(factor).add(i);
                while (num % factor == 0)   {
                    num /= factor;
                }
                factor++;
            }
            if (num == 1)
                continue;
            if (!primeFactorMap.containsKey(num))
                primeFactorMap.put(num, new HashSet<>());
            primeFactorMap.get(num).add(i);
            
        }
        
        
        for (Map.Entry<Integer, Set<Integer>> entry : primeFactorMap.entrySet())    {
            int key = entry.getKey();
            Set<Integer> valSet = entry.getValue();
            int pre = -1;
            for (int val : valSet)  {
                if (pre == -1)  {
                    pre = val;
                    continue;
                }
                
                uf.union(val, pre);
                pre = val;
            }
        }
        
        int[] sizeArr = new int[n];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            sizeArr[root]++;
            ans = Math.max(ans, sizeArr[root]);
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
    
    int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
    
    void union(int p, int q)    {
        int pRoot = find(p);
        int qRoot = find(q);
        
        if (pRoot == qRoot)
            return;
        
        if (rank[pRoot] > rank[qRoot])  {
            int t = pRoot;
            pRoot = qRoot;
            qRoot = t;
        }
        
        parent[pRoot] = qRoot;
        rank[qRoot]++;
        
        return;
    }
    
    
}