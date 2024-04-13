class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        long sum = 0;
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++)   {
            ans[i] += (int)((long)i * nums[i] - sum);
            sum += nums[i];
        }
        sum = 0;
        for (int i = n - 1; i >= 0; i--)   {
            ans[i] += (int)(sum - (long)(n - i - 1) * nums[i]);
            sum += nums[i];
        }
        
        return ans;
    }
}