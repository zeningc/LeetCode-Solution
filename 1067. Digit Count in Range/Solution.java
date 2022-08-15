class Solution {
    Integer[][] dp1;
    Integer[][] dp2;
    int d;
    public int digitsCount(int d, int low, int high) {
        this.d = d;
        int[] lowArr = getDigitArray(low - 1);
        int[] highArr = getDigitArray(high);
        dp1 = new Integer[highArr.length][1 << 10];
        dp2 = new Integer[lowArr.length][1 << 10];
        return dfs(dp1, highArr, 0, 0, true, false) - dfs(dp2, lowArr, 0, 0, true, false);
    }
    
    int dfs(Integer[][] dp, int[] nums, int idx, int cnt, boolean isLimit, boolean isNum)  {
        if (idx >= nums.length) {
            return isNum ? cnt : 0;
        }
        
        if (dp[idx][cnt] != null && !isLimit && isNum)
            return dp[idx][cnt];
        
        int ans = 0;
        
        if (!isNum)
            ans = dfs(dp, nums, idx + 1, cnt, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? nums[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            ans += dfs(dp, nums, idx + 1, cnt + (i == d ? 1 : 0), isLimit && nums[idx] == i, true);
        }
        
        if (!isLimit && isNum)
            dp[idx][cnt] = ans;
        
        return ans;
    }
    
    int[] getDigitArray(int n)  {
        int t = n;
        int len = 0;
        while (t != 0)  {
            len++;
            t /= 10;
        }
        
        int[] ans = new int[len];
        
        for (int i = len - 1; i >= 0; i--)  {
            ans[i] = n % 10;
            n /= 10;
        }
        
        return ans;
    }
}
