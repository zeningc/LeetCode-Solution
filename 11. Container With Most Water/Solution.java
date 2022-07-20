class Solution {
    public int maxArea(int[] nums) {
        int n = nums.length;
        int p = 0;
        int q = n - 1;
        int ans = 0;
        while (p < q)   {
            ans = Math.max(ans, Math.min(nums[p], nums[q]) * (q - p));
            if (nums[p] < nums[q])
                p++;
            else
                q--;
        }
        
        return ans;
    }
}
