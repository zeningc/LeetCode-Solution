class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }
    
    int helper(int[] nums, int k)   {
        int cnt = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int lo = 0;
        for (int hi = 0; hi < nums.length; hi++) {
            map.put(nums[hi], map.getOrDefault(nums[hi], 0) + 1);
            while (map.size() > k)  {
                map.put(nums[lo], map.get(nums[lo]) - 1);
                if (map.get(nums[lo]) == 0)
                    map.remove(nums[lo]);
                lo++;
            }
            cnt += hi - lo + 1;
        }
        
        return cnt;
    }
}