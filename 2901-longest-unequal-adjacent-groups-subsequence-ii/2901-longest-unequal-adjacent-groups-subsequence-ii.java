class Solution {
    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        int[][] dp = new int[n + 1][2];
        int maxLen = 0;
        int maxLenEnd = 0;
        for (int i = 1; i <= n; i++) {
            int bit = groups[i - 1];
            String word = words[i - 1];
            for (int j = 0; j < i; j++) {
                if (j != 0 && groups[j - 1] == bit)
                    continue;
                if (j != 0 && hammingDist(word, words[j - 1]) != 1)
                    continue;
                if (dp[i][0] < dp[j][0] + 1)    {
                    dp[i][0] = dp[j][0] + 1;
                    dp[i][1] = j;
                }
            }
            if (dp[i][0] > maxLen)  {
                maxLen = dp[i][0];
                maxLenEnd = i;
            }
        }
        
        List<String> ans = new ArrayList<>(maxLen);
        for (int p = maxLenEnd; p != 0; p = dp[p][1])
            ans.add(0, words[p - 1]);
        
        return ans;
    }
    
    int hammingDist(String a, String b) {
        if (a == null || b == null || a.length() != b.length())
            return Integer.MAX_VALUE;
        
        int cnt = 0;
        for (int i = 0; i < a.length(); i++)
            if (a.charAt(i) != b.charAt(i))
                cnt++;
        return cnt;
    }
}