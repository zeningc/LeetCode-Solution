class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        int l = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int ans = 0;
        for (int r = 0;  r < n; r++)    {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit)  {
                map.put(nums[l], map.getOrDefault(nums[l], 0) - 1);
                if (map.get(nums[l]) == 0)
                    map.remove(nums[l]);
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        
        return ans;
    }
}
