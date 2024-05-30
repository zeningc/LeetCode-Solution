class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        long sum = 0;
        int lo = 0;
        long ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int hi = 0; hi < nums.length; hi++)    {
            while (hi - lo + 1 > k || set.contains(nums[hi]))   {
                set.remove(nums[lo]);
                sum -= nums[lo];
                lo++;
            }
            set.add(nums[hi]);
            sum += nums[hi];
            if (hi - lo + 1 == k)
                ans = Math.max(ans, sum);
        }
        
        return ans;
    }
}