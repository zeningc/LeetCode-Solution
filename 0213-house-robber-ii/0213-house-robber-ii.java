class Solution {
    public int rob(int[] nums) {
        int pre = 0;
        int cur = 0;
        int ans = nums[0];
        for (int i = 0; i < nums.length - 1; i++)   {
            int nxt = pre + nums[i];
            ans = Math.max(ans, nxt);
            pre = Math.max(pre, cur);
            cur = nxt;
        }
        pre = 0;
        cur = 0;
        for (int i = 1; i < nums.length; i++)   {
            int nxt = pre + nums[i];
            ans = Math.max(ans, nxt);
            pre = Math.max(pre, cur);
            cur = nxt;
        }
        
        return ans;
    }
}