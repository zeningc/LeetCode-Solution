class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int sum = 0;
        for (int i = 1; i < n; i++) 
            sum += nums[i] - nums[0];
        
        ans[0] = sum;
        for (int i = 1; i < n; i++) {
            sum += i * (nums[i] - nums[i - 1]);
            sum -= (n - i) * (nums[i] - nums[i - 1]);
            ans[i] = sum;
        }
        
        return ans;
    }
}