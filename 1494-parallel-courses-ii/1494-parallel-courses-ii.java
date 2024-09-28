class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] pre = new int[n];
        int[] bitCnt = new int[1 << n];
        for (int i = 0; i < (1 << n); i++)
            bitCnt[i] = countBit(i);
        for (int[] relation : relations)    {
            int u = relation[0];
            int v = relation[1];
            pre[v - 1] |= (1 << (u - 1));
        }
        
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int state = 0; state < (1 << n); state++)  {
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                int preState = state - subset;
                if (bitCnt[subset] > k || dp[preState] >= Integer.MAX_VALUE / 2)
                    continue;
                int prerequesite = 0;
                for (int i = 0; i < n; i++)
                    if ((subset & (1 << i)) != 0)
                        prerequesite |= pre[i];
                if ((prerequesite & preState) != prerequesite)
                    continue;
                dp[state] = Math.min(dp[state], dp[preState] + 1);
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