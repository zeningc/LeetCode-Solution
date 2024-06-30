class Solution {
    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        int n = nums.length;
        int mod = (int)1e9 + 7;
        if (n == 1)
            return 0;
        if (n == 2)
            return (int)(((long)cost1 * Math.abs(nums[0] - nums[1])) % mod);
        cost2 = Math.min(cost1 * 2, cost2);
        long sum = 0;
        for (int num : nums)
            sum += num;
        Arrays.sort(nums);
        long ans = Long.MAX_VALUE;
        for (int limit = nums[n - 1]; limit <= nums[n - 1] * 2; limit++)    {
            long allDiff = (long)limit * n - sum;
            long diff0 = (long)limit - nums[0];
            long cur;
            if (diff0 * 2 >= allDiff) {
                cur = (allDiff - diff0) * cost2 + (diff0 - (allDiff - diff0)) * cost1;
            }
            else    {
                cur = (allDiff / 2) * cost2 + (allDiff % 2) * cost1;
            }
            ans = Math.min(ans, cur);
        }
        
        return (int)(ans % mod);
    }
}


/*

m * 2 + add * 2 < add * n

add > m/(n-2)
*/