class Solution {
    long MOD = (int)1e9 + 7;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        long a = dfs(new Long[24][401][2][2], getNum(num2), min_sum, max_sum, 0, 0, false, true);
        long b = dfs(new Long[24][401][2][2], getNum(num1), min_sum, max_sum, 0, 0, false, true);
        int sum = 0;
        for (char c : num1.toCharArray())
            sum += c - '0';
        long c = sum >= min_sum && sum <= max_sum ? 1 : 0;
        return (int)((MOD + a - b + c) % MOD);
    }
    
    long dfs(Long[][][][] mem, int[] nums, int minSum, int maxSum, int sum, int idx, boolean isNum, boolean isLimit)  {
        if (idx >= nums.length)
            return sum >= minSum && sum <= maxSum ? 1 : 0;
        if (mem[idx][sum][isNum ? 1 : 0][isLimit ? 1 : 0] != null)
            return mem[idx][sum][isNum ? 1 : 0][isLimit ? 1 : 0];
        
        long ans = 0;
        
        if (!isNum)
            ans = (ans + dfs(mem, nums, minSum, maxSum, sum, idx + 1, false, false)) % MOD;
        
        int lo = isNum ? 0 : 1;
        int hi = isLimit ? nums[idx] : 9;
        
        for (int i = lo; i <= hi; i++)  {
            if (sum + i > maxSum)
                continue;
            ans = (ans + dfs(mem, nums, minSum, maxSum, sum + i, idx + 1, true, isLimit && i == nums[idx])) % MOD;
        }
        
        mem[idx][sum][isNum ? 1 : 0][isLimit ? 1 : 0] = ans;
        return ans;
    }
    
    int[] getNum(String num)    {
        int[] nums = new int[num.length()];
        for (int i = 0; i < num.length(); i++)
            nums[i] = num.charAt(i) - '0';
        return nums;
    }
}