class Solution {
    public int maxSubarrays(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++)
            res &= nums[i];
        if (res > 0)
            return 1;
        int ans = 0;
        int pre = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++)   {
            pre &= nums[i];
            if (pre == 0)  {
                pre = Integer.MAX_VALUE;
                ans++;
            }
        }
        return ans;
    }
}