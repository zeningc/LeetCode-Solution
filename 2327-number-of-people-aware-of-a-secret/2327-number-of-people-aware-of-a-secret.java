class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        if (n <= 1)
            return 1;
        long[] tell = new long[n + 2];
        long[] delete = new long[n + 2];
        tell[Math.min(n + 1, 1 + delay)]++;
        delete[Math.min(n + 1, 1 + forget)]++;
        int mod = (int)1e9 + 7;
        long knowCnt = 1;
        long tellCnt = 0;
        for (int i = 2; i <= n; i++)    {
            tellCnt = (mod + tellCnt + tell[i] - delete[i]) % mod;
            tell[Math.min(n + 1, i + delay)] = (tell[Math.min(n + 1, i + delay)] + tellCnt) % mod;
            delete[Math.min(n + 1, i + forget)] = (delete[Math.min(n + 1, i + forget)] + tellCnt) % mod;
            knowCnt = (knowCnt + tellCnt) % mod;
            knowCnt = (mod + knowCnt - delete[i]) % mod;
        }
        return (int)(knowCnt % mod);
    }
}