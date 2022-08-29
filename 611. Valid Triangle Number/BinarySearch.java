class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int largest = nums[i] + nums[j] - 1;
                int smallest = nums[j] - nums[i] + 1;
                
                int left = j + 1;
                int right = n - 1;
                while (left <= right)   {
                    int mid = left + (right - left) / 2;
                    if (nums[mid] > largest)
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
                
                if (right < j + 1)
                    continue;
                
                int up = right;
                
                left = j + 1;
                right = n - 1;
                
                while (left <= right)   {
                    int mid = left + (right - left) / 2;
                    if (nums[mid] >= smallest)
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
                
                if (left == n)
                    continue;
                
                ans += up - left + 1;
            }
        }
        
        
        return ans;
    }
}
