class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        int maxLen = -1;
        int maxLenEnd = 0;
        int maxZeroLen = 0;
        int maxOneLen = 0;
        int maxZeroLenEnd = 0;
        int maxOneLenEnd = 0;
        for (int i = 1; i <= n; i++)    {
            if (groups[i - 1] == 0) {
                dp[i] = new int[] {words[i - 1].length() + maxOneLen, maxOneLenEnd};
                if (dp[i][0] > maxZeroLen)  {
                    maxZeroLen = dp[i][0];
                    maxZeroLenEnd = i;
                }
            }
            else    {
                dp[i] = new int[] {words[i - 1].length() + maxZeroLen, maxZeroLenEnd};
                if (dp[i][0] > maxOneLen)  {
                    maxOneLen = dp[i][0];
                    maxOneLenEnd = i;
                }
            }
            
            if (dp[i][0] > maxLen)   {
                maxLen = dp[i][0];
                maxLenEnd = i;
            }
        }
        
        List<String> ans = new LinkedList<>();
        while (dp[maxLenEnd][0] != 0)  {
            ans.add(0, words[maxLenEnd - 1]);
            maxLenEnd = dp[maxLenEnd][1];
        }
        
        return ans;
    }
}