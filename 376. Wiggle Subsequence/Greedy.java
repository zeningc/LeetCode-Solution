class Solution {
    public int wiggleMaxLength(int[] nums) {
        int prevDir = 0;
        int ans = 1;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            if (diff != 0 && diff * prevDir <= 0)    {
                ans++;
                prevDir = diff;
            }
        }
        
        return ans;
    }
}
