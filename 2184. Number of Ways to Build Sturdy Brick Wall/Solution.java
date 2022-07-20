class Solution {
    public int buildWall(int height, int width, int[] bricks) {
        Set<Integer> set = new HashSet<>();
        for (int b : bricks)
            set.add(b);
        long[][] dp = new long[height][1 << (width + 1)];
        int mod = (int)1e9 + 7;
        for (int state = 0; state < (1 << (width + 1)); state++)    {
            if (((state & 1) == 0) || ((state & (1 << width)) == 0))
                continue;
            int prev = 0;
            boolean flag = true;
            for (int j = 1; j < width + 1; j++) {
                if ((state & (1 << j)) != 0)    {
                    if (set.contains(j - prev)) {
                        prev = j;
                    }
                    else    {
                        flag = false;
                        break;
                    }
                }
            }
            
            if (flag)   {
                dp[0][state] = 1L;
            }
        }
        
        for (int i = 1; i < height; i++)    {
            for (int state = 0; state < (1 << (width + 1)); state++)    {
                if (((state & 1) == 0) || ((state & (1 << width)) == 0))
                    continue;
                if (dp[0][state] == 0)
                    continue;
                for (int prevState = 0; prevState < (1 << (width + 1)); prevState++)    {
                    if (((prevState & 1) == 0) || ((prevState & (1 << width)) == 0))
                    continue;
                    if (dp[0][prevState] == 0)
                        continue;
                    boolean flag = true;
                    for (int j = 1; j < width; j++) {
                        if ((state & (1 << j)) != 0 && (prevState & (1 << j)) != 0) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag)   {
                            dp[i][state] = (dp[i][state] + dp[i - 1][prevState]) % mod;
                    }
                }
            }
        }
        
        long sum = 0;
        for (int state = 0; state < (1 << (width + 1)); state++)    {
            sum = (sum + dp[height - 1][state]) % mod;
        }
        
        return (int)(sum % mod);
    }
}