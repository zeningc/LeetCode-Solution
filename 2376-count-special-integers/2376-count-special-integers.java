class Solution {
    int n;
    Integer[][] dp;
    int len;
    int[] digits;
    public int countSpecialNumbers(int n) {
        this.n = n;
        digits = new int[10];
        int t = n;
        len = 0;
        while (t > 0)   {
            t /= 10;
            len++;
        }
        t = n;
        
        for (int i = len - 1; i >= 0; i--)  {
            digits[i] = t % 10;
            t /= 10;
        }
        
        dp = new Integer[10][1 << 10]; // dp[i][j] means the # of special numbers starting from idx with mask
        
        return dfs(0, 0, false, true);
    }
    
    int dfs(int idx, int mask, boolean isNum, boolean isLimit)  {
        if (idx >= len)
            return isNum ? 1 : 0;
        
        if (isNum && !isLimit && dp[idx][mask] != null)
            return dp[idx][mask];
        
        int cnt = 0;
        
        if (!isNum)
            cnt += dfs(idx + 1, mask, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? digits[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            if ((mask & (1 << i)) == 0)
                cnt += dfs(idx + 1, mask | (1 << i), true, isLimit && i == hi);
        }
        
        if (isNum && !isLimit)
            dp[idx][mask] = cnt;
        
        return cnt;
    }
}

// n -> x digits
// ans = (<= x - 1) digits + x <= n

// dfs(idx, mask, isNum, isLimit) + dp[][]

// 54321
// 1xxxx isLimit = false
// 543xx isLimit = true