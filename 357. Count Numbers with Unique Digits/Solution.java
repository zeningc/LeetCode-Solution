class Solution {
    Integer[][] dp;
    int[] nums;
    int len;
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0)
            return 1;
        nums = new int[n];
        len = n;
        for (int i = 0; i < n; i++)
            nums[i] = 9;
        dp = new Integer[n + 1][1 << 10];
        return dfs(0, 0, true, false) + 1;
    }
    
    int dfs(int idx, int mask, boolean isLimit, boolean isNum)  {
        if (idx >= len)
            return isNum ? 1 : 0;
        
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
            
            ans += dfs(idx + 1, (mask | (1 << i)), isLimit && nums[idx] == i, true);
        }
        
        if (!isLimit && isNum)
            dp[idx][mask] = ans;
        
        return ans;
    }   
}
