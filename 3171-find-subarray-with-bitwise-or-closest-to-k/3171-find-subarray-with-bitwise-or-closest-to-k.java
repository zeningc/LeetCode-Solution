class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        int[][] presum = new int[n + 1][32];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 32; j++)
                presum[i + 1][j] = presum[i][j] + ((nums[i] & (1 << j)) == 0 ? 0 : 1);
        int lo = 0;
        int cur = 0;
        int ans = Integer.MAX_VALUE;
        for (int hi = 0; hi < nums.length; hi++)    {
            cur |= nums[hi];
            while (cur > k) {
                ans = Math.min(ans, Math.abs(cur - k));
                cur = 0;
                lo++;
                for (int i = 0; i < 32; i++)    {
                    int cnt = presum[hi + 1][i] - presum[lo][i];
                    if (cnt > 0)
                        cur |= (1 << i);
                }
            }
            if (lo <= hi)
                ans = Math.min(ans, Math.abs(cur - k));
        }
        
        return ans;
    }
}