class Solution {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 3; i >= 0; i--) {
            // Since the array is sorted in ascending order,
            // nums[i + 2] is the largest, nums[i + 1] is the middle, nums[i] is the smallest in the triplet
            if (nums[i] + nums[i + 1] > nums[i + 2]) {
                // Found a valid triangle
                return nums[i] + nums[i + 1] + nums[i + 2];
            }
        }
        // No valid triangle found
        return 0;
    }
}