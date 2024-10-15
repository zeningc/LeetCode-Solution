class Solution {
    public int longestSubarray(int[] nums, int limit) {
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        int lo = 0;
        int ans = 0;
        for (int hi = 0; hi < nums.length; hi++)    {
            freq.put(nums[hi], freq.getOrDefault(nums[hi], 0) + 1);
            
            while (freq.lastKey() - freq.firstKey() > limit)    {
                freq.put(nums[lo], freq.getOrDefault(nums[lo], 0) - 1);
                if (freq.get(nums[lo]) == 0)
                    freq.remove(nums[lo]);
                lo++;
            }
            
            ans = Math.max(ans, hi - lo + 1);
        }
        
        return ans;
    }
}