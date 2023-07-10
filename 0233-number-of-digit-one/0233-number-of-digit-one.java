class Solution {
    Integer[][] dp;
    int n;
    int len;
    int[] digit;
    public int countDigitOne(int n) {
        len = countForDigit(n);
        this.n = n;
        dp = new Integer[len + 1][10];
        digit = numToDigitArr(n, len);
        return dfs(0, 0, false, true);
    }
    
    int countForDigit(int n)    {
        int cnt = 0;
        while (n != 0)  {
            cnt++;
            n /= 10;
        }
        return cnt;
    }
    
    int[] numToDigitArr(int n, int len)  {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++)   {
            arr[len - i - 1] = n % 10;
            n /= 10;
        }
        return arr;
    }
    
    int dfs(int idx, int cnt, boolean isNum, boolean isLimit)   {
        if (idx >= len) {
            return cnt;
        }
        
        if (isNum && !isLimit && dp[idx][cnt] != null)
            return dp[idx][cnt];
        
        int ans = 0;
        
        if (!isNum)
            ans += dfs(idx + 1, cnt, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? digit[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            ans += dfs(idx + 1, i == 1 ? cnt + 1 : cnt, true, isLimit && i == digit[idx]);
        }
        
        if (isNum && !isLimit)
            dp[idx][cnt] = ans;
        
        return ans;
        
    }
}

