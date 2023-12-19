class Solution {
    public int[] solve(int[] nums, int[][] queries) {
        int n = nums.length;
        int margin = (int)Math.sqrt(n);
        int mod = (int)1e9 + 7;
        long[][] sufsum = new long[n][margin + 1];
        for (long[] suf : sufsum)
            Arrays.fill(suf, -1);

        for (int j = margin; j >= 1; j--)   {
            for (int i = n - 1; i >= 0; i--) {
                sufsum[i][j] = i == n - 1 || i + j >= n ? nums[i] : nums[i] + sufsum[i + j][j];
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        
        for (int i = 0; i < m; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            
            if (y > margin) {
                long sum = 0;
                for (int j = x; j < n; j += y)
                    sum += nums[j];
                ans[i] = (int)(sum % mod);
            }
            else    {
                ans[i] = (int)(sufsum[x][y] % mod);
            }
        }
        
        return ans;
    }
}