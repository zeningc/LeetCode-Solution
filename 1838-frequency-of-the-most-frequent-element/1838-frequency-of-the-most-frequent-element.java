class Solution {
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        int i = 0;
        Arrays.sort(nums);
        long sum = 0;
        int p = 0;
        int ans = 0;
        while (i < n)   {
            int j = i;
            while (j < n && nums[j] == nums[i]) {
                sum += nums[j];
                j++;
            }
            while ((long)(j - p) * nums[i] - sum > k)    {
                sum -= nums[p];
                p++;
            }
            ans = Math.max(ans, j - p);
            i = j;
        }
        
        return ans;
    }
}