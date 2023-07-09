class Solution {
    Integer[][] dp;
    int[] nums;
    int len;
    public int numDupDigitsAtMostN(int n) {
        nums = getDigitArray(n);
        len = nums.length;
        dp = new Integer[len][1 << 10];
        return n - dfs(0, 0, true, false);
    }
    
    int dfs(int idx, int mask, boolean isLimit, boolean isNum)  {
        if (idx >= len) {
            return isNum ? 1 : 0;
        }
        
        if (dp[idx][mask] != null && !isLimit && isNum)
            return dp[idx][mask];
        
        int ans = 0;
        if (!isNum)
            ans = dfs(idx + 1, mask, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? nums[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            if ((mask & (1 << i)) != 0)
                continue;
            
            ans += dfs(idx + 1, (mask | (1 << i)), isLimit && i == nums[idx], true);
        }
        
        dp[idx][mask] = ans;
        
        return ans;
    }
    
    
    int[] getDigitArray(int n)  {
        int t = n;
        int len = 0;
        while (t > 0)   {
            len++;
            t /= 10;
        }
        
        t = n;
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; i--)  {
            ans[i] = t % 10;
            t /= 10;
        }
        return ans;
    }
}