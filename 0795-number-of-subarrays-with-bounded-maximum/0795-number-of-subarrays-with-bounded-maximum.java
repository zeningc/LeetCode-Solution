class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int start = -1;
        int validMax = -1;
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] > right)    {
                start = -1;
                validMax = -1;
                continue;
            }
            
            if (start == -1)
                start = i;
            if (nums[i] >= left && nums[i] <= right)
                validMax = i;
            if (validMax != -1)
                ans += (validMax - start + 1);
        }
        return ans;
    }
}