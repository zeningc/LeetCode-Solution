class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }
    
    int helper(int[] nums, int cnt) {
        int n = nums.length;
        int ans = 0;
        int lo = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int hi = 0; hi < n; hi++)  {
            map.put(nums[hi], map.getOrDefault(nums[hi], 0) + 1);
            while (map.size() > cnt)    {
                map.put(nums[lo], map.get(nums[lo]) - 1);
                if (map.get(nums[lo]) == 0)
                    map.remove(nums[lo]);
                lo++;
            }
            ans += hi - lo + 1;
        }
        return ans;
    }
}