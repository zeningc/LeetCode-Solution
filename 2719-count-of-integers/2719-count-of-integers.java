class Solution {
    int MOD = 1000000007;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        int num1Len = num1.length();
        int num2Len = num2.length();
        long a =  (dfs(new Long[num2Len + 1][max_sum + 1], num2, num2Len, 0, max_sum, false, true) + MOD - dfs(new Long[num2Len][min_sum + 1], num2, num2Len, 0, min_sum - 1, false, true)) % MOD;
        long b = (dfs(new Long[num1Len + 1][max_sum + 1], num1, num1Len, 0, max_sum, false, true) + MOD - dfs(new Long[num1Len + 1][min_sum + 1], num1, num1Len, 0, min_sum - 1, false, true)) % MOD;
        long ret = (a + MOD - b) % MOD;
        if (getDigitSum(num1) >= min_sum && getDigitSum(num1) <= max_sum)
            ret = (ret + 1) % MOD;
        return (int)(ret % MOD);
    }
    
    int getDigitSum(String s)   {
        int ret = 0;
        for (char c : s.toCharArray())  {
            ret += c - '0';
        }
        return ret;
    }
    
    long dfs(Long[][] dp, String digits, int len, int idx, int left, boolean isNum, boolean isLimit)  {
        if (idx >= len)
            return isNum? 1 : 0;
        
        if (isNum && !isLimit && dp[idx][left] != null)
            return dp[idx][left];
        
        long ans = 0;
        
        if (!isNum) {
            ans = (ans + dfs(dp, digits, len, idx + 1, left, false , false)) % MOD;
        }
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? digits.charAt(idx) - '0' : 9;
        
        for (int i = lo; i <= hi; i++)  {
            if (i <= left)
                ans = (ans + dfs(dp, digits, len, idx + 1, left - i, true, isLimit && i == digits.charAt(idx) - '0')) % MOD;
        }
        
        if (isNum && !isLimit)
            dp[idx][left] = ans;
        
        return ans;
    }
}