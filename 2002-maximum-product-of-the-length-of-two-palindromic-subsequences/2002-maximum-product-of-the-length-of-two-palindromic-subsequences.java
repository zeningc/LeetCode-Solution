class Solution {
    public int maxProduct(String s) {
        int n = s.length();
        int ans = 0;
        for (int state = 1; state < (1 << n) - 1; state++)  {
            List<Integer> curStateIdxes = new ArrayList<>();
            List<Character> remainStr = new ArrayList<>();
            for (int i = 0; i < n; i++)
                if ((state & (1 << i)) != 0)
                    curStateIdxes.add(i);
                else
                    remainStr.add(s.charAt(i));
            
            int p = 0;
            int q = curStateIdxes.size() - 1;
            while (p < q)   {
                if (s.charAt(curStateIdxes.get(p)) != s.charAt(curStateIdxes.get(q)))
                    break;
                p++;
                q--;
            }
            if (p < q)
                continue;
            int m = remainStr.size();
            int[][] dp = new int[m][m];
            for (int i = 0; i < m; i++)
                dp[i][i] = 1;
            
            for (int len = 2; len <= m; len++)    {
                for (int i = 0; i < m - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                    if (remainStr.get(i) == remainStr.get(j))   {
                        if (i + 1 == j)
                            dp[i][j] = 2;
                        else
                            dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                    }
                }
            }
            
            ans = Math.max(ans, curStateIdxes.size() * dp[0][m - 1]);
        }
        
        return ans;
    }
}