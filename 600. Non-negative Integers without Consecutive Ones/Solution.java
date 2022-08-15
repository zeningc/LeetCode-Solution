class Solution {
    int len;
    int[] nums;
    Integer[][] dp;
    public int findIntegers(int n) {
        String binary = Integer.toString(n, 2);
        len = binary.length();
        nums = new int[len];
        dp = new Integer[len][2];
        for (int i = len - 1; i >= 0; i--)  {
            nums[i] = binary.charAt(i) - '0';
        }
        return dfs(0, 0, true, false) + 1;
    }
    
    int dfs(int idx, int last, boolean isLimit, boolean isNum)  {
        if (idx >= len) {
            return isNum ? 1 : 0;
        }
        
        if (dp[idx][last] != null && !isLimit && isNum)
            return dp[idx][last];
        
        int ans = 0;
        
        
        if (!isNum)
            ans = dfs(idx + 1, 0, false, false);
        
        if (isNum)
            ans += dfs(idx + 1, 0, isLimit && nums[idx] == 0, true);
        
        if (last != 1 && (!isLimit || isLimit && nums[idx] == 1))
            ans +=dfs(idx + 1, 1, isLimit && nums[idx] == 1, true);
        if (!isLimit && isNum)
            dp[idx][last] = ans;
        return ans;
    }
}
