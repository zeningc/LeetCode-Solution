class Solution {
    public long maxArrayValue(int[] nums) {
        int n = nums.length;
        long ret = 0;
        long sum = 0;
        for (int i = nums.length - 1; i >= 0; i--)  {
            if (sum >= nums[i]) {
                sum += nums[i];
            }
            else    {
                sum = nums[i];
            }
            ret = Math.max(ret, sum);
        }
        
        return ret;
    }
}