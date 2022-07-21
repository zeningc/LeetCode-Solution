class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] lis = new int[n];
        int idx = -1;
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = idx;
            int target = nums[i];
            
            while (left <= right)   {
                int mid = left + (right - left) / 2;
                if (lis[mid] >= nums[i])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            
            if (left > idx)
                idx++;
            lis[left] = nums[i];
        }
        return idx + 1;
    }
}
