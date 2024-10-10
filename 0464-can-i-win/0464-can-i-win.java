class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int totalSum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (totalSum < desiredTotal)
            return false;
        return dfs(new HashMap<>(), maxChoosableInteger, desiredTotal, 0, 0);
    }
    
    boolean dfs(Map<Integer, Boolean> cache, int maxInteger, int total, int sum, int state)  {
        if (cache.containsKey(state))
            return cache.get(state);
        for (int i = 1; i <= maxInteger; i++)   {
            if ((state & (1 << i)) != 0)
                continue;
            if (sum + i >= total)
                return true;
            
            if (!dfs(cache, maxInteger, total, sum + i, state | (1 << i)))   {
                cache.put(state, true);
                return true;
            }
        }
        
        cache.put(state, false);
        return false;
    }
}