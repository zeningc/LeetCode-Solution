class Solution {
    public int countPrimes(int n) {
        boolean[] primes = new boolean[n];
        Arrays.fill(primes, true);
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (!primes[i])
                continue;
            cnt++;
            for (long j = (long)i * i; j < n; j += i)  {
                primes[(int)j] = false;
            }
        }
        
        return cnt;
    }
}