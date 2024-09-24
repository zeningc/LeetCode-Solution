class Solution {
    public int numberOfGoodSubsets(int[] nums) {
        int[] primes = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        Map<Integer, Integer> numToState = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        
        for (int i = 2; i <= 30; i++)   {
            if (!freq.containsKey(i))
                continue;
            int num = i;
            int state = 0;
            for (int j = 0; j < primes.length && num != 1; j++) {
                int d = primes[j];
                if (num % d != 0)
                    continue;
                if (num % (d * d) == 0)
                    break;
                state |= (1 << j);
                num /= d;
            }
            if (num != 1)
                continue;
            numToState.put(i, state);
        }
        int mod = (int)1e9 + 7;
        long[] dp = new long[1 << primes.length];
        dp[0] = 1;
        for (Map.Entry<Integer, Integer> entry: numToState.entrySet()) {
            long[] newDP = dp.clone();
            int num = entry.getKey();
            int state = entry.getValue();
            for (int i = 0; i < (1 << primes.length); i++)  {
                if ((state & i) != 0)
                    continue;
                newDP[state + i] = (newDP[state + i] + dp[i] * freq.get(num)) % mod;
            }
            dp = newDP;
        }
        long power = 1;
        for (int i = 0; i < freq.getOrDefault(1, 0); i++)
            power = (power * 2) % mod;
        long ans = 0;
        for (int i = 1; i < (1 << primes.length); i++)  {
            ans = (ans + dp[i] * power) % mod;
        }
        
        return (int)ans;
    }
}