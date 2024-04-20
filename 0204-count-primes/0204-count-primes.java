class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i])
                continue;
            cnt++;
            for (long j = (long)i * i; j < n; j += i)
                isPrime[(int)j] = false;
        }
        return cnt;
    }
}