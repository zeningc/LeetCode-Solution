class Solution {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int bestPlan = 0;
        int bestScore = 0;
        for (int state = 0; state < (1 << 12); state++) {
            int flag = 0;
            int remain = numArrows;
            int pnt = 0;
            for (int j = 1; j < 12; j++)    {
                if ((state & (1 << j)) != 0)    {
                    if (remain < aliceArrows[j] + 1)    {
                        flag = 1;
                        break;
                    }
                    remain -= aliceArrows[j] + 1;
                    pnt += j;
                }
            }
            if (flag == 0)  {
                if (pnt > bestScore)    {
                    bestScore = pnt;
                    bestPlan = state;
                }
            }
        }
        int[] ans = new int[12];
        int remain = numArrows;
        for (int j = 1; j < 12; j++)    {
            if ((bestPlan & (1 << j)) != 0) {
                ans[j] = aliceArrows[j] + 1;
                remain -= aliceArrows[j] + 1;
            }
        }
        ans[0] = remain;
        
        return ans;
    }   
}