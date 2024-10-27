class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long ans = 0;
        long curSum = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int lo = 0;
        for (int hi = 0; hi < nums.length; hi++)    {
            freq.put(nums[hi], freq.getOrDefault(nums[hi], 0) + 1);
            curSum += nums[hi];
            if (hi - lo + 1 > k)    {
                freq.put(nums[lo], freq.getOrDefault(nums[lo], 0) - 1);
                if (freq.get(nums[lo]) == 0)
                    freq.remove(nums[lo]);
                curSum -= nums[lo];
                lo++;
            }
            if (freq.size() == k)
                ans = Math.max(ans, curSum);
        }
        
        return ans;
    }
}