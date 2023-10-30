class Solution {
    public int minimumOperations(List<Integer> nums) {
        int n = nums.size();
        int[][] dp = new int[n][4];
        dp[0][1] = nums.get(0) == 1 ? 0 : 1;
        dp[0][2] = nums.get(0) == 2 ? 0 : 1;
        dp[0][3] = nums.get(0) == 3 ? 0 : 1;
        for (int i = 1; i < nums.size(); i++)
        {
            dp[i][1] = (nums.get(i) == 1 ? 0 : 1) + (dp[i - 1][1]);
            dp[i][2] = (nums.get(i) == 2 ? 0 : 1) + (Math.min(dp[i - 1][1], dp[i - 1][2]));
            dp[i][3] = (nums.get(i) == 3 ? 0 : 1) + (Math.min(dp[i - 1][1], Math.min(dp[i - 1][2], dp[i - 1][3])));
        }
        
        return Math.min(dp[n - 1][1], Math.min(dp[n - 1][2], dp[n - 1][3]));
    }
}