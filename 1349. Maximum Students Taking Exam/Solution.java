class Solution {
    int m;
    int n;
    public int maxStudents(char[][] seats) {
        m = seats.length;
        n = seats[0].length;
        int[] seatsState = new int[m + 1];
        seatsState[0] = (1 << n);
        for (int j = 0; j < seats.length; j++)   {
            char[] seat = seats[j];
            int state = 0;
            for (int i = 0; i < seat.length; i++)   {
                if (seat[i] == '.')
                    state |= (1 << i);
            }
            seatsState[j + 1] = state;
        }
        
        
        int[][] dp = new int[m + 1][1 << n];
        
        for (int i = 1; i <= m; i++)    {
            for (int state = 0; state < (1 << n); state++)  {
                if (!valid(state, seatsState[i]))
                        continue;
                for (int prevState = 0; prevState < (1 << n); prevState++)  {
                    if (!valid(prevState, seatsState[i - 1]))
                        continue;
                    if (conflict(prevState, state))
                        continue;
                    dp[i][state] = Math.max(dp[i][state], dp[i - 1][prevState] + count(state));
                }
            }
        }
        
        int ans = 0;
        for (int state = 0; state < (1 << n); state++)
            ans = Math.max(ans, dp[m][state]);
        return ans;
    }
    
    boolean valid(int state, int seat)  {
        for (int i = 0; i < n; i++) {
            if ((state & (1 << i)) != 0 && (seat & (1 << i)) == 0)
                return false;
            if ((state & (1 << i)) != 0 && (state & (1 << (i + 1))) != 0)
                return false;
        }
        return true;
    }
    
    boolean conflict(int prev, int curr)    {
        for (int i = 0; i < n; i++) {
            if ((prev & (1 << i)) != 0) {
                if ((curr & (1 << (i - 1))) != 0 || (curr & (1 << (i + 1))) != 0)
                    return true;
            }
        }
        
        return false;
    }
    
    int count(int state)    {
        int cnt = 0;
        while (state != 0)  {
            cnt++;
            state &= (state - 1);
        }
        return cnt;
    }
}
