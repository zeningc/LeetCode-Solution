class Solution {
    public int rob(int[] nums) {
        int pre = 0;
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int nxt = pre + nums[i];
            pre = Math.max(pre, cur);
            cur = nxt;
        }
        return Math.max(cur, pre);
    }
}