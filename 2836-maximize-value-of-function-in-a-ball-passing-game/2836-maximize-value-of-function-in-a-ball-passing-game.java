class Solution {
    public long getMaxFunctionValue(List<Integer> receiver, long k) {
        long ans = 0;
        int m = 35;
        int n = receiver.size();
        int[][] jump = new int[n][m];
        long[][] val = new long[n][m];
        
        for (int i = 0; i < n; i++) {
            jump[i][0] = receiver.get(i);
            val[i][0] = receiver.get(i);
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int nextHop = jump[j][i - 1];
                jump[j][i] = jump[nextHop][i - 1];
                val[j][i] = val[j][i - 1] + val[nextHop][i - 1];
            }
        }
        
        for (int i = 0; i < n; i++) {
            long curVal = i;
            int cur = i;
            for (int j = 0; j < m; j++) {
                if ((k & (1L << j)) != 0L) {
                    curVal += val[cur][j];
                    cur = jump[cur][j];
                }
            }
            ans = Math.max(ans, curVal);
        }
        
        return ans;
    }
}


// dp[i][j] i x^j dest
// dp1[i][j] i x^j value - id