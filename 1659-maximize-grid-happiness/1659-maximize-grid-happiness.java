class Solution {
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        int stateCnt = (int)Math.pow(3, n);
        int[][][][] dp = new int[m + 1][introvertsCount + 1][extrovertsCount + 1][stateCnt];
        for (int i = 0; i <= m; i++)
            for (int j = 0; j <= introvertsCount; j++)
                for (int k = 0; k <= extrovertsCount; k++)
                    for (int state = 0; state < stateCnt; state++)
                        dp[i][j][k][state] = -1;
        
        dp[0][introvertsCount][extrovertsCount][0] = 0;
        
        for (int i = 1; i <= m; i++)    {
            for (int j = 0; j <= introvertsCount; j++)  {
                for (int k = 0; k <= extrovertsCount; k++)  {
                    for (int state = 0; state < stateCnt; state++)  {
                        int[] curState = ternaryStateToArr(state);
                        int iCnt = 0;
                        int eCnt = 0;
                        for (int cur : curState)
                            if (cur == 1)
                                iCnt++;
                            else if (cur == 2)
                                eCnt++;
                        if (iCnt + j > introvertsCount || eCnt + k > extrovertsCount)
                            continue;
                        
                        for (int pre = 0; pre < stateCnt; pre++) {
                            if (dp[i - 1][iCnt + j][eCnt + k][pre] == -1)
                                continue;
                            dp[i][j][k][state] = Math.max(dp[i][j][k][state], dp[i - 1][iCnt + j][eCnt + k][pre] + calc(pre, state));
                        }
                    }
                }
            }
        }
        
        int ans = 0;
        for (int j = 0; j <= introvertsCount; j++)
            for (int k = 0; k <= extrovertsCount; k++)
                for (int state = 0; state < stateCnt; state++)
                    ans = Math.max(ans, dp[m][j][k][state]);
        
        return ans;
    }
    int calc(int pre, int cur)  {
        int[] preState = ternaryStateToArr(pre);
        int[] curState = ternaryStateToArr(cur);
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            if (preState[i] == 1 && curState[i] != 0)
                sum -= 30;
            if (preState[i] == 2 && curState[i] != 0)
                sum += 20;
            
            if (curState[i] == 1)
                sum += 120;
            
            if (curState[i] == 2)
                sum += 40;
            
            if (curState[i] == 1 && (i > 0 && curState[i - 1] != 0))
                sum -= 30;
            if (curState[i] == 1 && (i < 5 && curState[i + 1] != 0))
                sum -= 30;
            
            if (curState[i] == 1 && preState[i] != 0)
                sum -= 30;
            
            if (curState[i] == 2 && (i > 0 && curState[i - 1] != 0))
                sum += 20;
            if (curState[i] == 2 && (i < 5 && curState[i + 1] != 0))
                sum += 20;
            
            if (curState[i] == 2 && preState[i] != 0)
                sum += 20;
        }
        
        return sum;
    }
    int[] ternaryStateToArr(int state)  {
        int[] ret = new int[6];
        int idx = 0;
        while (state != 0)  {
            ret[idx++] = state % 3;
            state /= 3;
        }
        return ret;
    }
}