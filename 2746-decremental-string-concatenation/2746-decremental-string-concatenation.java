class Solution {
    public int minimizeConcatenatedLength(String[] words) {
        int n = words.length;
        int[][][] dp = new int[n][26][26];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 26; j++)
                for (int k = 0; k < 26; k++)
                    dp[i][j][k] = Integer.MAX_VALUE / 2;
        dp[0][words[0].charAt(0) - 'a'][words[0].charAt(words[0].length() - 1) - 'a'] = words[0].length();
        for (int i = 1; i < n; i++) {
            String word = words[i];
            char first = word.charAt(0);
            char last = word.charAt(word.length() - 1);
            for (int j = 0; j < 26; j++)    {
                for (int k = 0; k < 26; k++)    {
                    dp[i][j][last - 'a'] = Math.min(dp[i][j][last - 'a'], dp[i - 1][j][k] + word.length() + (k == first - 'a' ? -1 : 0));
                    dp[i][first - 'a'][k] = Math.min(dp[i][first - 'a'][k], dp[i - 1][j][k] + word.length() + (j == last - 'a' ? -1 : 0));
                }
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < 26; j++)
                ans = Math.min(ans, dp[n - 1][i][j]);
        
        return ans;
    }
}