class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int p = 0;
        int q = 0;
        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();
        int n = nums.length;
        int ans = 0;
        for (int hi = 0; hi < n; hi++)  {
            freq1.put(nums[hi], freq1.getOrDefault(nums[hi], 0) + 1);
            freq2.put(nums[hi], freq2.getOrDefault(nums[hi], 0) + 1);
            
            while (freq1.size() >= k)    {
                freq1.put(nums[p], freq1.get(nums[p]) - 1);
                if (freq1.get(nums[p]) == 0)
                    freq1.remove(nums[p]);
                p++;
            }
            
            while (freq2.size() >= k + 1)    {
                freq2.put(nums[q], freq2.get(nums[q]) - 1);
                if (freq2.get(nums[q]) == 0)
                    freq2.remove(nums[q]);
                q++;
            }
            
            ans += p - q;
        }
        
        return ans;
    }
}