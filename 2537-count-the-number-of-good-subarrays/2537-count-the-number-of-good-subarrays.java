class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int cnt = 0;
        int lo = 0;
        long ans = 0;
        int n = nums.length;
        for (int hi = 0; hi < n; hi++)  {
            int curFreq = freq.getOrDefault(nums[hi], 0);
            cnt -= curFreq * (curFreq - 1) / 2;
            freq.put(nums[hi], curFreq + 1);
            cnt += curFreq * (curFreq + 1) / 2;
            
            while (cnt >= k) {
                curFreq = freq.get(nums[lo]);
                cnt -= curFreq * (curFreq - 1) / 2;
                cnt += (curFreq - 1) * (curFreq - 2) / 2;
                freq.put(nums[lo], curFreq - 1);
                lo++;
            }
            
            ans += lo;
        }
        
        return ans;
    }
}