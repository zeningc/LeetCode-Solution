class Solution {
    public long minimumTime(int[] power) {
        int n = power.length;
        long[] dp = new long[1 << n];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0L;
        for (int state = 0; state < (1 << n); state++)  {
            if (dp[state] >= Long.MAX_VALUE / 2)
                continue;
            int cntOfBit = countBit(state) + 1;
            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) == 0)    {
                    int nxtState = state | (1 << i);
                    long day = power[i] / cntOfBit + (power[i] % cntOfBit == 0 ? 0 : 1);
                    dp[nxtState] = Math.min(dp[nxtState], dp[state] + day);
                }
            }
        }
        
        return dp[(1 << n) - 1];
    }
    
    int countBit(int state) {
        int cnt = 0;
        while (state != 0)  {
            cnt++;
            state &= (state - 1);
        }
        
        return cnt;
    }
}