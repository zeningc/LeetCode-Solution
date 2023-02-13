class Solution {
    public int minimumTime(String s) {
        int n = s.length();
        if (n == 1)
            return s.charAt(0) == '1' ? 1 : 0;
        
        int[] dp1 = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                dp1[i] = i == 0 ? 0 : dp1[i - 1]; 
            }
            else    {
                dp1[i] = i == 0 ? 1 : Math.min(i + 1, dp1[i - 1] + 2);
            }
        }
        
        int[] dp2 = new int[n];
        for (int i = n - 1; i >= 0; i--)    {
            if (s.charAt(i) == '0') {
                dp2[i] = i == n - 1 ? 0 : dp2[i + 1];
            }
            else    {
                dp2[i] = i == n - 1 ? 1 : Math.min(n - i, dp2[i + 1] + 2);
            }
        }
        int ans = dp1[n - 1];
        for (int i = 0; i < n - 1; i++) {
            ans = Math.min(ans, dp1[i] + dp2[i + 1]);
        }
        return ans;
    }
}