class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] == 0)
                continue;
            int k = i + 2;
            for (int j = i + 1; j < n - 1; j++) {
                while (k < n && nums[k] < nums[i] + nums[j])
                    k++;
                ans += k - j - 1;
            }
        }
        
        return ans;
    }
}
