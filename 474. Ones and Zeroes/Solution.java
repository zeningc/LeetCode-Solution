class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        List<int[]> nums = new ArrayList<>(len);
        
        for (String str : strs) {
            int cnt = 0;
            for (char c : str.toCharArray())    {
                cnt += c == '1' ? 1 : 0;
            }
            nums.add(new int[] {cnt, str.length() - cnt});
        }
        
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 0; i < nums.size(); i++)   {
            for (int j = n; j >= nums.get(i)[0]; j--)   {
                for (int k = m; k >= nums.get(i)[1]; k--)   {
                    dp[j][k] = Math.max(dp[j][k], dp[j - nums.get(i)[0]][k - nums.get(i)[1]] + 1);
                }
            }
        }
        
        return dp[n][m];
    }
}
