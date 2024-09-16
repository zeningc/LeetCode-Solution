class Solution {
    public int numOfWays(int n) {
        long[][] dp = new long[n][28];
        int mod = (int)1e9 + 7;
        for (int i = 0; i < 28; i++)    {
            int[] cur = stateToArr(i);
            if (!validateState(cur))
                continue;
            dp[0][i]++;
        }
        for (int i = 1; i < n; i++)    {
            for (int j = 0; j < 28; j++)    {
                int[] cur = stateToArr(j);
                if (!validateState(cur))
                    continue;
                for (int k = 0; k < 28; k++)    {
                    int[] pre = stateToArr(k);
                    if (!validateState(pre))
                        continue;
                    if (!validateTwoState(pre, cur))
                        continue;
                    dp[i][j] = (dp[i][j] + dp[i - 1][k]) % mod;
                }
            }
        }
        
        long ans = 0;
        for (int i = 0; i < 28; i++)
            ans = (ans + dp[n - 1][i]) % mod;
        return (int)ans;
    }
    boolean validateTwoState(int[] pre, int[] cur)  {
        return pre[0] != cur[0] && pre[1] != cur[1] && pre[2] != cur[2];
    }
    boolean validateState(int[] arr)    {
        return arr[0] != arr[1] && arr[1] != arr[2];
    }
    
    int[] stateToArr(int state)  {
        int[] ret = new int[3];
        for (int i = 2; i >= 0; i--)    {
            ret[i] = state % 3;
            state /= 3;
        }
        return ret;
    }
    
    int arrToState(int[] arr)   {
        int ret = 1;
        for (int i = 0; i < 3; i++)
            ret = ret * 3 + arr[i];
        return ret;
    }
}