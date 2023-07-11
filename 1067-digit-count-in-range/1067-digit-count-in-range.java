class Solution {
    int d;
    public int digitsCount(int d, int low, int high) {
        return dfs(new Integer[getLen(high)][10], getDigits(high), getLen(high), d, 0, 0, false, true) - dfs(new Integer[getLen(low - 1)][10], getDigits(low - 1), getLen(low - 1), d, 0, 0, false, true);
    }
    
    int dfs(Integer[][] dp, int[] digits, int len, int d, int idx, int cnt, boolean isNum, boolean isLimit)    {
        if (idx >= len)
            return isNum ? cnt : 0;
        
        if (isNum && !isLimit && dp[idx][cnt] != null)
            return dp[idx][cnt];
        
        int ans = 0;
        
        if (!isNum)
            ans += dfs(dp, digits, len, d, idx + 1, cnt, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? digits[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            ans += dfs(dp, digits, len, d, idx + 1, i == d ? cnt + 1 : cnt, true, isLimit && digits[idx] == i);
        }
        
        if (isNum && !isLimit)
            dp[idx][cnt] = ans;
        
        return ans;
    }
    
    int[] getDigits(int num)    {
        int len = getLen(num);
        int[] digits = new int[len];
        for (int i = 0; i < len; i++)   {
            digits[len - i - 1] = num % 10;
            num /= 10;
        }
        return digits;
    }
    
    int getLen(int num) {
        int len = 0;
        while (num > 0) {
            num /= 10;
            len++;
        }
        return len;
    }
}