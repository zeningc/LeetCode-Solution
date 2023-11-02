class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[][] dp = new int[n + 1][2];
        dp[0][0] = 0;
        dp[0][1] = 0;
        int maxLen = -1;
        int maxLenEnd = -1;
        for (int i = 1; i <= n; i++)    {
            int len = words[i - 1].length();
            int pre = 0;
            for (int j = 1; j < i; j++) {
                if (1 - groups[i - 1] != groups[j - 1])
                    continue;
                if (len < dp[j][0] + words[i - 1].length())    {
                    len = dp[j][0] + words[i - 1].length();
                    pre = j;
                }
            }
            dp[i] = new int[] {len, pre};
            if (len > maxLen)   {
                maxLen = len;
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