class Solution {
    public long countSubarrays(int[] nums, int k) {
        Map<Integer, Long> map = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < nums.length; i++)   {
            Map<Integer, Long> newMap = new HashMap<>();
            newMap.put(nums[i], 1L);
            for (Map.Entry<Integer, Long> e : map.entrySet())   {
                int key = e.getKey();
                long val = e.getValue();
                newMap.put(key & nums[i], newMap.getOrDefault(key & nums[i], 0L) + val);
            }
            
            map = newMap;
            ans += map.getOrDefault(k, 0L);
        }
        
        return ans;
    }
}