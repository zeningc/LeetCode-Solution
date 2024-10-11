class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(nums[0], 1);
        dp.put(-nums[0], dp.getOrDefault(-nums[0], 0) + 1);
        for (int i = 1; i < n; i++) {
            Map<Integer, Integer> newDP = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : dp.entrySet()) {
                int key = entry.getKey();
                int val = entry.getValue();
                newDP.put(key + nums[i], newDP.getOrDefault(key + nums[i], 0) + val);
                newDP.put(key - nums[i], newDP.getOrDefault(key - nums[i], 0) + val);
            }
            dp = newDP;
        }
        
        return dp.getOrDefault(target, 0);
    }
}

/*

dp[i][j] ith jit

*/