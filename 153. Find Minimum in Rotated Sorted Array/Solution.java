class Solution {
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int start = nums[0];
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (nums[mid] < start)
                right = mid - 1;
            else
                left = mid + 1;
        }
        
        return left == n ? nums[0] : nums[left];
    }
}
