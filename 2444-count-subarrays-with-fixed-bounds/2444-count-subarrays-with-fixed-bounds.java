class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int lastBreak = -1;
        int n = nums.length;
        long ans = 0;
        if (minK > maxK)
            return 0l;
        int lastMin = -1;
        int lastMax = -1;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] < minK || nums[i] > maxK)   {
                lastMin = -1;
                lastMax = -1;
                lastBreak = i;
                continue;
            }
            
            if (minK == maxK)   {
                if (nums[i] == minK)    {
                    ans += (i - lastBreak);
                }
            }
            else if (nums[i] == minK)    {
                lastMin = i;
                if (lastMax != -1)  {
                    ans += (lastMax - lastBreak);
                }
            }
            else if (nums[i] == maxK)    {
                lastMax = i;
                if (lastMin != -1)  {
                    ans += (lastMin - lastBreak);
                }
            }
            else if (lastMin != -1 && lastMax != -1) {
                    ans += Math.min(lastMin, lastMax) - lastBreak;
            }
        }
        
        return ans;
    }

}