class Solution {
    public int maximumProcessableQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        
        int[][] dp = new int[n][n];
        for (int len = n; len >= 1; len--)  {
            for (int i = 0; i < n - len + 1; i++)  {
                int j = i + len - 1;
                if (i > 0)  {
                    if (dp[i - 1][j] < m && queries[dp[i - 1][j]] <= nums[i - 1])
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                    else
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
                if (j < n - 1)  {
                    if (dp[i][j + 1] < m && queries[dp[i][j + 1]] <= nums[j + 1])
                        dp[i][j] = Math.max(dp[i][j], dp[i][j + 1] + 1);
                    else
                        dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
                }
                
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] <= 0)
                    continue;
                ans = Math.max(ans, dp[i][j]);
                if (dp[i][j] < m && i == j && nums[i] >= queries[dp[i][j]]) {
                    ans = Math.max(ans, dp[i][j] + 1);
                }
            }
        }
        
        return ans;
    }
}

/*
dp[i][j]
dp[i - 1][j] = t => nums[i] > queries[t]

dp[i][]

*/