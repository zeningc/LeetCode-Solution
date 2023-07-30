class Solution {
    int len;
    Long[][] dp;
    int[] digits;
    int MOD = (int)1e9 + 7;
    public int countSteppingNumbers(String low, String high) {
        len = low.length();
        dp = new Long[len][10];
        digits = strToDigitArray(low);
        long lo = dfs(0, -1, false, true);
        len = high.length();
        dp = new Long[len][10];
        digits = strToDigitArray(high);
        long hi = dfs(0, -1, false, true);
        return (int)((hi + MOD - lo + (isStepping(low) ? 1 : 0)) % MOD);
    }
    
    boolean isStepping(String num)  {
        for (int i = 1; i < num.length(); i++)  {
            if (Math.abs(num.charAt(i) - num.charAt(i - 1)) != 1)
                return false;
        }
        return true;
    }
    
    int[] strToDigitArray(String low)   {
        int[] num = new int[low.length()];
        for (int i = 0; i < low.length(); i++)  {
            num[i] = low.charAt(i) - '0';
        }
        return num;
    }
    
    long dfs(int idx, int lastDigit, boolean isNum, boolean isLimit)   {
        if (idx >= len) {
            return isNum ? 1 : 0;
        }
        if (isNum && !isLimit && dp[idx][lastDigit] != null)
            return dp[idx][lastDigit];
        long ans = 0;
        
        if (!isNum) 
            ans = (ans + dfs(idx + 1, -1, false, false)) % MOD;
        
        for (int i = 0; i < 10; i++)    {
            if (!isNum && i == 0)
                continue;
            if (lastDigit != -1 && lastDigit + 1 != i && lastDigit - 1 != i)
                continue;
            if (isLimit && i > digits[idx])
                continue;
            ans = (ans + dfs(idx + 1, i, true, isLimit && i == digits[idx])) % MOD;
        }
        
        if (isNum && !isLimit)
            dp[idx][lastDigit] = ans;
        
        return ans;        
    }
}