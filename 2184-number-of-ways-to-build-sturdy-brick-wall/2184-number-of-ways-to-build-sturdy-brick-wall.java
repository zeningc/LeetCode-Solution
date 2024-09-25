class Solution {
    public int buildWall(int height, int width, int[] bricks) {
        boolean[] valid = new boolean[11];
        for (int brick : bricks)
            valid[brick] = true;
        List<Integer> validStates = new ArrayList<>();
        for (int i = 0; i < (1 << (width + 1)); i++) {
            if ((i & 1) == 0 || (i & (1 << width)) == 0)
                continue;
            int preOne = 0;
            boolean validFlag = true;
            for (int j = 1; j < (width + 1); j++)    {
                if ((i & (1 << j)) == 0)
                    continue;
                int len = j - preOne;
                if (!valid[len])    {
                    validFlag = false;
                    break;
                }
                preOne = j;
            }
            
            if (!validFlag)
                continue;
            validStates.add(i);
        }
        
        
        long[] dp = new long[1 << (width + 1)];
        int mod = (int)1e9 + 7;
        for (int state : validStates)
            dp[state] = 1;
        
        for (int i = 1; i < height; i++)    {
            long[] newDP = new long[1 << (width + 1)];
            for (int cur : validStates)   {
                for (int pre : validStates) {
                    if ((cur & pre) != (1 | (1 << width)))
                        continue;
                    newDP[cur] = (newDP[cur] + dp[pre]) % mod;
                }
            }
            dp = newDP;
        }
        
        long ans = 0;
        for (long d : dp)
            ans = (ans + d) % mod;
        return (int)ans;
    }
}