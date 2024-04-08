class Solution {
    public int minOperations(int[][] grid, int x) {
        int n = grid.length;
        int m = grid[0].length;
        int[] nums = new int[n * m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i * m + j] = grid[i][j];
            }
        }
        
        Arrays.sort(nums);
        for (int i = 1; i < m * n; i++)
            if ((nums[i] - nums[i - 1]) % x != 0)
                return -1;

        int mid = nums[n * m / 2];
        int ans = Integer.MAX_VALUE;
        return helper(nums, mid, x);
    }
    
    int helper(int[] nums, int target, int x)  {
        int ans = 0;
        for (int num : nums)
            ans += Math.abs(num - target) / x;
        return ans;
    }
}