class Solution {
    public int minimumCost(String target, String[] words, int[] costs) {
        int n = target.length();
        long BASE = (long)26;
        int MOD = (int)1e9 + 7;
        long[] targetHash = new long[n + 1];
        long[] hashBase = new long[n + 1];
        hashBase[0] = 1;
        for (int i = 1; i <= n; i++)    {
            hashBase[i] = (hashBase[i - 1] * BASE) % MOD;
            targetHash[i] = ((targetHash[i - 1] * BASE) % MOD + (target.charAt(i - 1) - 'a'));
        }
        
        Map<Integer, Map<Long, Long>> lenToHashMap = new HashMap<>();
        for (int i = 0; i < words.length; i++)  {
            long hash = 0;
            for (char c : words[i].toCharArray())  {
                hash = ((hash * BASE) % MOD + (c - 'a')) % MOD;
            }
            lenToHashMap.computeIfAbsent(words[i].length(), x -> new HashMap<>()).merge(hash, (long)costs[i], Long::min);
        }
        
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 1; i <= n; i++)   {
            for (Map.Entry<Integer, Map<Long, Long>> entry : lenToHashMap.entrySet())  {
                int len = entry.getKey();
                if (len > i)
                    continue;
                Map<Long, Long> map = entry.getValue();
                long subHash = (targetHash[i] - (hashBase[len] * targetHash[i - len]) % MOD + MOD) % MOD;
                dp[i] = Math.min(dp[i], dp[i - len] + map.getOrDefault(subHash, (long)Integer.MAX_VALUE / 2));
            }
        }
        
        if (dp[n] >= Integer.MAX_VALUE / 2)
            return -1;
        return (int)(dp[n] % MOD);
    }
}