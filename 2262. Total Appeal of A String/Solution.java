class Solution {
    public long appealSum(String s) {
        long ans = 0;
        int n = s.length();
        int[] dp = new int[26];
        Arrays.fill(dp, -1);
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            ans += (long)(i - dp[c - 'a']) * (n - i);
            dp[c - 'a'] = i;
        }
        
        return ans;
    }
}