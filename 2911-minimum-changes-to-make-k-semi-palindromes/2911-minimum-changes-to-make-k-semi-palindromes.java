class Solution {
    Map<String, Integer> cache;
    public int minimumChanges(String s, int k) {
        cache = new HashMap<>();
        int n = s.length();
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 0; i <= k; i++)
            for (int j = 0; j <= n; j++)
                dp[i][j] = Integer.MAX_VALUE / 2;
        dp[0][0] = 0;
        
        for (int i = 1; i <= k; i++)    {
            for (int j = i; j <= n; j++)    {
                for (int x = j - 1; x >= i - 1; x--)    {
                    int replaceCnt = calcMinReplace(s.substring(x, j));
                    if (replaceCnt == -1)
                        continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][x] + replaceCnt);
                }
            }
        }
        return dp[k][n];
    }
    
    int calcMinReplace(String s)    {
        int len = s.length();
        if (len <= 1)
            return -1;
        if (cache.containsKey(s))
            return cache.get(s);
        int ans = Integer.MAX_VALUE / 2;
        for (int d = 1; d < len && 2 * d - 1 < len; d++)    {
            if (len % d != 0)
                continue;
            int cnt = 0;
            for (int lo = 0; lo < d; lo++)  {
                int p = lo;
                int q = len - d + lo;
                while (p < q)   {
                    if (s.charAt(p) != s.charAt(q))
                        cnt++;
                    p += d;
                    q -= d;
                }
            }
            ans = Math.min(ans, cnt);
        }
        cache.put(s, ans);
        return ans;
    }
}