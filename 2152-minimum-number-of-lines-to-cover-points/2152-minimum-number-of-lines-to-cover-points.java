class Solution {
    public int minimumLines(int[][] points) {
        int n = points.length;
        int[] dp = new int[1 << n];
        boolean[] pointInLine = new boolean[1 << n];
        for (int state = 0; state < (1 << n); state++)  {
            int x1 = -1;
            int y1 = -1;
            int x2 = -1;
            int y2 = -1;
            int cnt = 0;
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if ((state & (1 << i)) != 0)  {
                    if (cnt == 0)    {
                        cnt++;
                        x1 = points[i][0];
                        y1 = points[i][1];
                        continue;
                    }
                    if (cnt == 1)   {
                        cnt++;
                        x2 = points[i][0];
                        y2 = points[i][1];
                        continue;
                    }
                    cnt++;
                    int x3 = points[i][0];
                    int y3 = points[i][1];
                    if ((y2 - y1) * (x3 - x1) != (y3 - y1) * (x2 - x1)) {
                        valid = false;
                        break;
                    }
                }
            }
            
            if (valid)
                pointInLine[state] = true;
        }
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int state = 0; state < (1 << n); state++)  {
            for (int subset = state; subset > 0; subset = (subset - 1) & state) {
                if (dp[state - subset] >= Integer.MAX_VALUE / 2 || !pointInLine[subset])
                    continue;
                
                dp[state] = Math.min(dp[state], dp[state - subset] + 1);
            }
        }
        
        return dp[(1 << n) - 1];
    }
}