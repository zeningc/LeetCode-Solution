class Solution {
    int mod = (int)1e9 + 7;
    Map<Integer, List<Integer>> m;
    long[] dp;
    long[] childCnt;
    Map<Long, Long> factorialCache;
    Map<Long, Long> invCache;
    public int waysToBuildRooms(int[] prevRoom) {
        m = new HashMap<>();
        dp = new long[prevRoom.length];
        factorialCache = new HashMap<>();
        invCache = new HashMap<>();
        childCnt = new long[prevRoom.length];
        for (int i = 0; i < prevRoom.length; i++)
            m.computeIfAbsent(prevRoom[i], x -> new ArrayList<>()).add(i);
        dfs(0);
        return (int)(dp[0] % mod);
    }
    
    void dfs(int u) {
        if (!m.containsKey(u)) {
            dp[u] = 1;
            childCnt[u] = 1;
            return;
        }
        for (int v : m.get(u))
             dfs(v);
        long totalCnt = 0;
        for (int v : m.get(u))
            totalCnt += childCnt[v];
        long curVal = factorial(totalCnt);
        for (int v : m.get(u))
            curVal = (curVal * inv(factorial(childCnt[v]))) % mod;
        for (int v : m.get(u))
            curVal = (curVal * dp[v]) % mod;
        dp[u] = curVal;
        childCnt[u] = totalCnt + 1;
    }
    
    long quickPow(long x, long y)   {
        long ret = 1;
        long cur = x;
        while (y != 0) 
        {
            if ((y & 1) != 0) 
            {
                ret = (long)ret * cur % mod;
            }
            cur = (long)cur * cur % mod;
            y >>= 1;
        }
        return ret;
    }

    long inv(long x)    {
        if (!invCache.containsKey(x))
            invCache.put(x, quickPow(x, mod - 2));
        return invCache.get(x);
    }
    
    long factorial(long x)  {
        if (x == 0 || x == 1)
            return 1;
        if (!factorialCache.containsKey(x))
            factorialCache.put(x, (x * factorial(x - 1)) % mod) ;
        return factorialCache.get(x);
    }
}