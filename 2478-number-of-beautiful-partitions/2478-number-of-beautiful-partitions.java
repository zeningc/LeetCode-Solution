class Solution {
    public int beautifulPartitions(String s, int k, int minLength) {
        Set<Character> set = new HashSet<>(Arrays.asList('2', '3', '5', '7'));
        if (!set.contains(s.charAt(0)) || set.contains(s.charAt(s.length() - 1)))
            return 0;
        int mod = (int)1e9 + 7;
        int n = s.length();
        long[][] dp = new long[n][k + 1];
        
        int[] primes = new int[n];
        primes[0] = 0;
        for (int i = 1; i < n; i++) {
            if (set.contains(s.charAt(i)) && !set.contains(s.charAt(i - 1)))  {
                primes[i] = i;
            }
            else    {
                primes[i] = primes[i - 1];
            }
        }
        
        long[][] presum = new long[n + 1][k + 1];
        
        for (int i = 0; i < n; i++)    {
            if (i < minLength - 1 || set.contains(s.charAt(i)))  {
                dp[i][1] = 0;
                continue;
            }
            dp[i][1] = 1;
        }
        
        for (int i = minLength - 1; i < n; i++)    {
            presum[i + 1][1] = presum[i][1];
            if (i == n - 1 || !set.contains(s.charAt(i)) && set.contains(s.charAt(i + 1)))  {
                presum[i + 1][1] = (presum[i + 1][1] + dp[i][1]) % mod;
            }
        }
        
        
        for (int j = 2; j <= k; j++)    {
            for (int i = j * minLength - 1; i < n; i++)    {
                if (set.contains(s.charAt(i)))  {
                    dp[i][j] = 0;
                    continue;
                }
                int idx = primes[i - minLength + 1];
                dp[i][j] = presum[idx][j - 1];
            }
            
            for (int i = j * minLength - 1; i < n; i++) {
                presum[i + 1][j] = presum[i][j];
                if (i == n - 1 || !set.contains(s.charAt(i)) && set.contains(s.charAt(i + 1)))  {
                    presum[i + 1][j] = (presum[i + 1][j] + dp[i][j]) % mod;
                }
            }
        }
        
        return (int)dp[n - 1][k];
    }
}
