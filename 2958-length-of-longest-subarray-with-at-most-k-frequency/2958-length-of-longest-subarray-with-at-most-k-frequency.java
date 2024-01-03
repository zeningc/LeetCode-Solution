class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> m = new HashMap<>();
        int ans = 0;
        int lo = 0;
        for (int hi = 0; hi < n; hi++) {
            m.put(nums[hi], m.getOrDefault(nums[hi], 0) + 1);
            while (m.get(nums[hi]) > k) {
                m.put(nums[lo], m.getOrDefault(nums[lo], 0) - 1);
                lo++;
            }
            ans = Math.max(ans, hi - lo + 1);
        }
        
        return ans;
    }
}