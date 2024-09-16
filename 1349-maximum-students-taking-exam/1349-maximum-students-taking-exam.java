class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        
        int[][] dp = new int[m][1 << n];
        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], -1);
        for (int i = 0; i < m; i++)
            dp[i][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < (1 << n); j++)  {
                boolean valid = true;
                int cnt = 0;
                for (int k = 0; k < n; k++)    {
                    if ((j & (1 << k)) != 0)    {
                        if (seats[i][k] == '.') {
                            if (k != 0 && (j & (1 << (k - 1))) != 0) {
                                valid = false;
                                break;
                            }
                            cnt++;
                            continue;
                        }
                        valid = false;
                        break;
                    }
                }
                if (!valid)
                    continue;
                dp[i][j] = cnt;
                if (i == 0) {
                    continue;
                }
                for (int pre = 0; pre < (1 << n); pre++)    {
                    if (dp[i - 1][pre] == -1)
                        continue;
                    for (int k = 0; k < n; k++)    {
                        if ((j & (1 << k)) == 0)
                            continue;
                        if (k != 0 && (pre & (1 << (k - 1))) != 0)  {
                            valid = false;
                            break;
                        }
                        if (k != n - 1 && (pre & (1 << (k + 1))) != 0)  {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) {
                        valid = true;
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][pre] + cnt);
                }
            }
        }
        
        
        int ans = 0;
        for (int i = 0; i < (1 << n); i++)
            ans = Math.max(ans, dp[m - 1][i]);
        
        return ans;
    }
                                
                                
}