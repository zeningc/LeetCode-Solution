class Solution {
    public int minTransfers(int[][] transactions) {
        int[] balance = new int[12];
        for (int[] transaction : transactions)  {
            balance[transaction[0]] += transaction[2];
            balance[transaction[1]] -= transaction[2];
        }
        int[] stateSum = new int[1 << 12];
        for (int state = 0; state < (1 << 12); state++) {
            int sum = 0;
            for (int i = 0; i < 12; i++)    {
                if ((state & (1 << i)) == 0)
                    continue;
                sum += balance[i];
            }
            stateSum[state] = sum;
        }
        
        int[] dp = new int[1 << 12];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        
        for (int state = 0; state < (1 << 12); state++) {
            if (stateSum[state] != 0)
                continue;
            dp[state] = Math.max(0, countBit(state) - 1);
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                if (stateSum[subset] == 0)
                    dp[state] = Math.min(dp[state], dp[state - subset] + dp[subset]);
            }
        }
        
        return dp[(1 << 12) - 1];
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