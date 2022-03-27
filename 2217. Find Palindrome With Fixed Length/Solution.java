class Solution {
    int[] dp;
    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = queries.length;
        long[] ans = new long[n];
        if (intLength <= 2) {
            int m = intLength == 2 ? 11 : 1;
            for (int i = 0; i < n; i++) {
                ans[i] = queries[i] > 9 ? -1 : queries[i] * m;
            }
            return ans;
        }
        dp = new int[16];
        dp[1] = 10;
        dp[2] = 10;
        for (int i = 3; i <= intLength; i++)    {
            dp[i] = dp[i - 2] * 10;
        }
        int all = 9 * dp[intLength - 2];
        for (int i = 0; i < n; i++) {
            int cnt = queries[i] - 1;
            if (cnt + 1 > all)  {
                ans[i] = -1;
                continue;
            } 
            int edge = (cnt / dp[intLength - 2]) + 1;
            String edgeStr = String.valueOf(edge);
            String numStr = edgeStr + generate(intLength - 2, cnt % dp[intLength - 2]) + edgeStr;
            ans[i] = Long.parseLong(numStr);
        }
        
        return ans;
    }
    
    
    String generate(int len, int cnt) {
        if (len == 1)
            return String.valueOf(cnt);
        if (len == 2)
            return String.valueOf(cnt) + String.valueOf(cnt);
        String edgeStr = String.valueOf(cnt / dp[len - 2]);
        return edgeStr + generate(len - 2, cnt % dp[len - 2]) + edgeStr;
    }
    
}
