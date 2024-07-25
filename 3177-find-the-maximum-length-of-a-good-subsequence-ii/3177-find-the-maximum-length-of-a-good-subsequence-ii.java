class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[k + 1][n];
        
        int ans = 0;
        int[][][] pre = new int[n + 1][2][2];
        for (int i = 0; i < n; i++) {
            pre[i][0][0] = -1;
            pre[i][0][1] = 0;
            pre[i][1][0] = -1;
            pre[i][1][1] = 0;
        }
        
        for (int x = 0; x <= k; x++)    {
            int nxtFirstKey = -1;
            int nxtFirstValue = 0;
            int nxtSecondKey = -1;
            int nxtSecondValue = 0;
            Map<Integer, Integer> max = new HashMap<>();
            int[][][] nxt = new int[n + 1][2][2];
            nxt[0][0][0] = -1;
            nxt[0][0][1] = 0;
            nxt[0][1][0] = -1;
            nxt[0][1][1] = 0;
            
            for (int y = 0; y < n; y++) {
                
                if (x != 0)  {
                    if (pre[y][0][0] != nums[y])
                        dp[x][y] = Math.max(dp[x][y], pre[y][0][1] + 1);
                    else if (pre[y][1][0] != nums[y])
                        dp[x][y] = Math.max(dp[x][y], pre[y][1][1] + 1);
                    
                }
                
                dp[x][y] = Math.max(dp[x][y], max.getOrDefault(nums[y], 0) + 1);
                
                if (nxtFirstKey == -1 || nxtFirstKey != -1 && nxtFirstValue <= dp[x][y]) {
                    if (nxtFirstKey != -1 && nxtFirstValue <= dp[x][y]) {
                        nxtSecondKey = nxtFirstKey;
                        nxtSecondValue = nxtFirstValue;
                    }
                    nxtFirstKey = nums[y];
                    nxtFirstValue = dp[x][y];
                }
                else if (nxtSecondKey == -1 || nxtSecondKey != -1 && nxtSecondValue <= dp[x][y])   {
                    nxtSecondKey = nums[y];
                    nxtSecondValue = dp[x][y];
                }
                
                nxt[y + 1][0][0] = nxtFirstKey;
                nxt[y + 1][0][1] = nxtFirstValue;
                nxt[y + 1][1][0] = nxtSecondKey;
                nxt[y + 1][1][1] = nxtSecondValue;
                
                max.put(nums[y], Math.max(max.getOrDefault(nums[y], 0), dp[x][y]));
                ans = Math.max(ans, dp[x][y]);
            }
            
            pre = nxt;
        }
        
        return ans;
    }
}