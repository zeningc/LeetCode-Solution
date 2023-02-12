class Solution {
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = i == 0 ? Integer.MAX_VALUE : Math.min(nums[i - 1], left[i - 1]);
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] = i == n - 1 ? Integer.MIN_VALUE : Math.max(nums[i + 1], right[i + 1]);
        }
        for (int i = 1; i < n; i++) {
            if (left[i] < nums[i] && right[i] > nums[i])
                return true;
        }
        return false;
    }
}