class Solution {
    Integer[][] dp;
    int[] nums;
    int len;
    public int countDigitOne(int n) {
        if (n == 0) return 0;
        int t = n;
        len = 0;
        while (t != 0)  {
            t /= 10;
            len++;
        }
        nums = new int[len];
        t = n;
        for (int i = len - 1; i >= 0; i--)  {
            nums[i] = t % 10;
            t /= 10;
        }
        
        dp = new Integer[len][len + 1];
        
        return dfs(0, 0, true, false);
    }
    
    int dfs(int idx, int cnt, boolean isLimit, boolean isNum)   {
        if (idx >= len) {
            return cnt;
        }
        
        if (dp[idx][cnt] != null && !isLimit && isNum)
            return dp[idx][cnt];
        int ans = 0;
        if (!isNum)
            ans = dfs(idx + 1, cnt, false, false);
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? nums[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            ans += dfs(idx + 1, cnt + (i == 1 ? 1 : 0), isLimit && (i == nums[idx]), true);
        }
        
        dp[idx][cnt] = ans;
        return ans;
    }
}
