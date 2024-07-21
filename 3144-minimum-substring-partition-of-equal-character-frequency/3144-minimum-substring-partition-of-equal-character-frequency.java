class Solution {
    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        int[][] presum = new int[n + 1][26];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 26; j++)
                presum[i + 1][j] = (presum[i][j] + (j == (s.charAt(i) - 'a') ? 1 : 0));
        
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= n; i++)    {
            for (int j = i - 1; j >= 0; j--)    {
                int freq = -1;
                boolean equal = true;
                for (int k = 0; k < 26; k++)    {
                    int charCnt = (presum[i][k] - presum[j][k]);
                    if (charCnt == 0)
                        continue;
                    if (freq == -1)
                        freq = charCnt;
                    if (charCnt != freq)    {
                        equal = false;
                        break;
                    }
                }
                if (equal)  {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        
        return dp[n];
    }
}