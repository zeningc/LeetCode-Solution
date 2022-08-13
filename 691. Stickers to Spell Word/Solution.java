class Solution {
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        
        for (int i = 0; i < stickers.length; i++)   {
            for (int state = 0; state < (1 << n); state++)  {
                int newState = getNewState(target, stickers[i], state);
                dp[newState] = Math.min(dp[newState], dp[state] + 1);     
            }
        }
        
        return dp[(1 << n) - 1] >= Integer.MAX_VALUE / 2 ? -1 : dp[(1 << n) - 1];
    }
    
    int getNewState(String target, String s, int state)   {
        for (char c : s.toCharArray())  {
            for (int i = 0; i < target.length(); i++)   {
                if ((state & (1 << i)) != 0)
                    continue;
                if (c == target.charAt(i))  {
                    state |= (1 << i);
                    break;
                }
            }
        }
        
        return state;
    }
}
