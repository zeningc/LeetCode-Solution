class Solution {
    public int colorTheGrid(int m, int n) {
        int stateSize = (int)Math.pow(3, m);
        long[] dp = new long[stateSize];
        int mod = (int)1e9 + 7;
        List<Integer> validStates = new ArrayList<>();
        for (int cur = 0; cur < stateSize; cur++) {
            if (!validate(m, cur))
                continue;
            validStates.add(cur);
            dp[cur] = 1;
        }
        
        
        for (int i = 1; i < n; i++) {
            long[] newDP = new long[stateSize];
            for (int cur : validStates) {
                if (!validate(m, cur))
                    continue;
                for (int pre : validStates)   {
                    if (!validate(m, cur) || !validate(m, pre, cur))
                        continue;
                    newDP[cur] = (newDP[cur] + dp[pre]) % mod;
                }
            }
            dp = newDP;
        }
        
        long ans = 0;
        for (int i = 0; i < stateSize; i++)
            ans = (ans + dp[i]) % mod;
        
        return (int)ans;
    }
    
    boolean validate(int m, int state) {
        int[] arr = ternaryToArr(m, state);
        for (int i = 1; i < m; i++)
            if (arr[i] == arr[i - 1])
                return false;
        return true;
    }
    
    boolean validate(int m, int pre, int cur) {
        int[] preArr = ternaryToArr(m, pre);
        int[] curArr = ternaryToArr(m, cur);
        for (int i = 0; i < m; i++)
            if (preArr[i] == curArr[i])
                return false;
        return true;
    }
    
    int[] ternaryToArr(int m, int state)   {
        int[] arr = new int[m];
        int i = 0;
        while (i < m && state != 0)  {
            arr[i++] = state % 3;
            state /= 3;
        }
        
        return arr;
    }
}