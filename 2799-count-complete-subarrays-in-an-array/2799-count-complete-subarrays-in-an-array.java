class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int cnt = 0;
        int n = nums.length;
        int lo = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        int distinctCnt = set.size();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int hi = 0; hi < n; hi++)  {
            freq.put(nums[hi], freq.getOrDefault(nums[hi], 0) + 1);
            while (freq.getOrDefault(nums[lo], 0) > 1 && freq.size() == distinctCnt) {
                freq.put(nums[lo], freq.get(nums[lo]) - 1);
                if (freq.get(nums[lo]) == 0)
                    freq.remove(nums[lo]);
                lo++;
            }
            if (freq.size() == distinctCnt) {
                cnt += lo + 1;
            }
        }
        
        return cnt;
    }
}