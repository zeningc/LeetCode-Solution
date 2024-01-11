class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        Arrays.sort(nums);
        long[] presum = new long[n + 1];
        int p = 0;
        int ans = 0;
        while (i < n)   {
            int j = i;
            while (j < n && nums[j] == nums[i]) {
                presum[j + 1] = presum[j] + nums[j];
                j++;
            }
            int target = nums[i];
            while ((long)(i - p) * target - (presum[i] - presum[p]) > k)
                p++;
            ans = Math.max(ans, j - p);
            i = j;
        }
        
        return ans;
    }
}