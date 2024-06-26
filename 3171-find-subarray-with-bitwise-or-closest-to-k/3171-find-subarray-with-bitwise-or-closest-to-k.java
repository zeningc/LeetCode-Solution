class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int[][] presum = new int[n + 1][32];
        
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < 32; j++)
                presum[i][j] = presum[i - 1][j] + ((nums[i - 1] & (1 << j)) == 0 ? 0 : 1);
        
        int res = 0;
        int lo = 0;
        int ans = Integer.MAX_VALUE;
        for (int hi = 0; hi < n; hi++) {
            res |= nums[hi];
            while (res > k) {
                ans = Math.min(ans, Math.abs(res - k));
                lo++;
                res = 0;
                for (int j = 0; j < 32; j++)
                    if (presum[hi + 1][j] - presum[lo][j] > 0)
                        res |= (1 << j);
            }
            if (lo <= hi)
                ans = Math.min(ans, Math.abs(res - k));
        }
        
        return ans;
    }
}