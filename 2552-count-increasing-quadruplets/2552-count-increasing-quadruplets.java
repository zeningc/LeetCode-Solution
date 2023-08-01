class Solution {
    public long countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][] pre = new int[n][n + 1];
        int[][] post = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            for (int j = 0; j < n; j++) {
                pre[j][v] = (j >= 1 ? pre[j - 1][v] : 0);
                if (nums[j] < v)
                    pre[j][v]++;
            }
        }
        
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            for (int j = n - 1; j >= 0; j--) {
                post[j][v] = (j < n - 1 ? post[j + 1][v] : 0);
                if (nums[j] > v)
                    post[j][v]++;
            }
        }
        long ans = 0;
        for (int k = 0; k < n - 1; k++) {
            for (int j = 1; j < k; j++) {
                if (nums[k] > nums[j])
                    continue;
                ans += (long)post[k + 1][nums[j]] * pre[j - 1][nums[k]];
            }
        }
        
        return ans;
    }
}