class Solution {
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        Map<Integer, List<Integer>> hatToPerson = new HashMap<>();
        int mod = (int)1e9 + 7;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < hats.get(i).size(); j++)
                hatToPerson.computeIfAbsent(hats.get(i).get(j), x -> new ArrayList<>()).add(i);
        
        long[] dp = new long[1 << n];
        dp[0] = 1L;
        for (int i = 1; i <= 40; i++)   {
            long[] nextDp = dp.clone();
            for (int state = 0; state < (1 << n); state++)  {
                if (dp[state] == 0)
                    continue;
                for (int j : hatToPerson.getOrDefault(i, new ArrayList<>()))    {
                    if ((state & (1 << j)) != 0)
                        continue;
                    nextDp[state | (1 << j)] = (nextDp[state | (1 << j)] + dp[state]) % mod;
                }
            }
            dp = nextDp;
        }
        
        return (int)dp[(1 << n) - 1];
    }
}