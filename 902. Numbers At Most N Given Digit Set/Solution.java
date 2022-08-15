class Solution {
    char[] digits;
    Integer[] dp;
    char[] nums;
    int len;
    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.digits = new char[digits.length];
        for (int i = 0; i < digits.length; i++) {
            this.digits[i] = digits[i].charAt(0);
        }
        Arrays.sort(this.digits);
        nums = String.valueOf(n).toCharArray();
        len = nums.length;
        dp = new Integer[len];
        return dfs(0, true, false);
    }
    
    int dfs(int idx, boolean isLimit, boolean isNum)    {
        if (idx >= len) {
            return isNum ? 1 : 0;
        }
        
        if (dp[idx] != null && !isLimit && isNum)
            return dp[idx];
        
        int ans = 0;
        
        if (!isNum)
            ans = dfs(idx + 1, false, false);
        
        for (int i = 0; i < digits.length; i++) {
            if (isLimit && digits[i] > nums[idx])
                break;
            ans += dfs(idx + 1, isLimit && (digits[i] == nums[idx]), true);
        }
        if (!isLimit && isNum)
            dp[idx] = ans;
        return ans;
    }   
}
