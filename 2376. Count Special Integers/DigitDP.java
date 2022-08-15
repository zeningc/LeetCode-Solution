class Solution {
    int[] nums;
    Integer[][] dp;
    int len;
    public int countSpecialNumbers(int n) {
        len = getLengthOfNum(n);
        nums = new int[len];
        dp = new Integer[len + 1][1 << 10];
        getDigitArray(n, nums);
        return dfs(0, 0, true, false);
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
        
        for (int i = lo; i <= hi; i++)   {
            if ((mask & (1 << i)) != 0)
                continue;
            ans += dfs(idx + 1, (mask | (1 << i)), isLimit && (i == nums[idx]), true);
        }
        if (!isLimit && isNum)
            dp[idx][mask] = ans;
        return ans;
    }

    int getLengthOfNum(int n)   {
        int t = n;
        int cnt = 0;
        while (t != 0)  {
            cnt++;
            t /= 10;
        }
        return cnt;
    }
    
    void getDigitArray(int n, int[] arr)   {
        int idx = arr.length - 1;
        for (int i = idx; i >= 0; i--)  {
            arr[i] = n % 10;
            n /= 10;
        }
    }
}
