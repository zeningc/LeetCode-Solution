class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int start = nums[0];
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            
            if (target < start) {
                if (nums[mid] >= start || nums[mid] < target)  {
                    left = mid + 1;
                }
                else    {
                    right = mid - 1;
                }
            }
            else    {
                if (nums[mid] < start || nums[mid] > target)  {
                    right = mid - 1;
                }
                else    {
                    left = mid + 1;
                }
            }
        }
        
        return -1;
    }
}
