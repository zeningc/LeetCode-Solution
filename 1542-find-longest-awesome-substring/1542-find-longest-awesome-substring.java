class Solution {
    public int longestAwesome(String s) {
        int n = s.length();
        int[] dp = new int[1 << 11];
        Arrays.fill(dp, -1);
        dp[0] = -1;
        int len = 1;
        int state = 0;
        for (int i = 0 ; i < n; i++)
        {
            int cur = s.charAt(i) - '0';
            state ^= (1 << cur);
            if (state == 0 || dp[state] != -1)
            {
                len = Math.max(len, i - dp[state]);
            }

            for (int j = 0; j < 10; j++)
            {
                int preState = (state ^ (1 << j));
                if (preState == 0 || dp[preState] != -1)
                {
                    len = Math.max(len, i - dp[preState]);
                }
            }

            if (state != 0 && dp[state] == -1)
                dp[state] = i;
        }
        
        return len;
    }
}


