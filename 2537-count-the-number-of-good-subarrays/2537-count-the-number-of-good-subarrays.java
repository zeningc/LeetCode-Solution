class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        long cnt = 0;
        long ans = 0;
        int lo = 0;
        for (int hi = 0; hi < nums.length; hi++)   {
            if (freq.containsKey(nums[hi]))
                cnt += freq.get(nums[hi]);
            freq.put(nums[hi], freq.getOrDefault(nums[hi], 0) + 1);
            
            while (cnt >= k)    {
                freq.put(nums[lo], freq.get(nums[lo]) - 1);
                cnt -= freq.get(nums[lo]);
                if (freq.get(nums[lo]) == 0)
                    freq.remove(nums[lo]);
                lo++;
            }
            
            ans += lo;
        }
        
        return ans;
    }
}