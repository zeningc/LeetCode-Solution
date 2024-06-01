class Solution {
    public int numberOfStableArrays(int zero, int one, int limit) {
        long[][] dp0 = new long[zero + 1][one + 1];
        long[][] dp1 = new long[zero + 1][one + 1];
        int mod = (int)1e9 + 7;
        dp0[0][0] = 1;
        dp1[0][0] = 1;
        long[][] presum0 = new long[zero + 1][one + 1];
        long[][] presum1 = new long[zero + 1][one + 1];
        presum0[0][0] = 1;
        presum1[0][0] = 1;
        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++)  {
                if (i == 0 && j == 0)
                    continue;
                dp0[i][j] = (dp0[i][j] + (getVal(presum1, i - 1, j) - getVal(presum1, i - limit - 1, j) + mod) % mod) % mod;
                dp1[i][j] = (dp1[i][j] + (getVal(presum0, i, j - 1) - getVal(presum0, i, j - limit - 1) + mod) % mod) % mod;
                presum0[i][j] = (getVal(presum0, i, j - 1) + dp0[i][j]) % mod;
                presum1[i][j] = (getVal(presum1, i - 1, j) + dp1[i][j]) % mod;
            }
        }
        
        return (int)((dp0[zero][one] + dp1[zero][one]) % mod);
    }
    
    long getVal(long[][] presum, int i, int j)    {
        if (i < 0)
            return 0;
        if (j < 0)
            return 0;
        i = Math.min(presum.length - 1, i);
        j = Math.min(presum[0].length - 1, j);
        return presum[i][j];
    }
}



/*

*/