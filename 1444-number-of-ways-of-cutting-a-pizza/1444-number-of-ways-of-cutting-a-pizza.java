class Solution {
    int m;
    int n;
    int mod = (int)1e9 + 7;
    int[][] presum;
    Map<String, Long> map = new HashMap<>();
    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        char[][] matrix = new char[m][];
        for (int i = 0; i < m; i++) {
            matrix[i] = pizza[i].toCharArray();
        }
        presum = new int[m + 1][n + 1];
        int[][] p = presum;
        for (int i = 1; i <= m; i++)    {
            for (int j = 1; j <= n; j++)    {
                presum[i][j] = presum[i - 1][j] + presum[i][j - 1] - presum[i - 1][j - 1] + (matrix[i - 1][j - 1] == 'A' ? 1 : 0);
            }
        }
        return (int)(dfs(0, 0, m - 1, n - 1, k - 1) % mod);
    }
    
    long dfs(int lx, int ly, int rx, int ry, int cut)  {
        if (cut == 0)
            return 1;
        String key = lx + "_" + ly + "_" + rx + "_" + ry + "_" + cut;
        if (map.containsKey(key))
            return map.get(key);
        long ans = 0;
        for (int i = ly; i < ry; i++)   {
            if (contain(lx, ly, rx, i) > 0 && contain(lx, i + 1, rx, ry) > 0)   {
                ans = (ans + dfs(lx, i + 1, rx, ry, cut - 1)) % mod;
            }
        }
        
        for (int i = lx; i < rx; i++)   {
            if (contain(lx, ly, i, ry) > 0 && contain(i + 1, ly, rx, ry) > 0)   {
                ans = (ans + dfs(i + 1, ly, rx, ry, cut - 1)) % mod;
            }
        }
        map.put(key, ans);
        return ans;
    }
    
    int contain(int lx, int ly, int rx, int ry) {
        return presum[rx + 1][ry + 1] - presum[lx][ry + 1] - presum[rx + 1][ly] + presum[lx][ly];
    }
}