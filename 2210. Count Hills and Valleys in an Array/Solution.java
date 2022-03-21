class Solution {
    public int countHillValley(int[] nums) {
        int n = nums.length;
        int idx = 0;
        int ans = 0;
        while (idx < n) {
            int j = idx + 1;
            while (j < n && nums[j] == nums[idx])
                j++;
            if (idx > 0 && j < n && (nums[idx - 1] < nums[idx] && nums[j] < nums[idx] || nums[idx - 1] > nums[idx] && nums[j] > nums[idx])) {
                ans++;
            }
            idx = j;
        }
        return ans;
    }
}