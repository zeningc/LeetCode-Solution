class Solution {
    public int stringCount(int n) {
        if (n < 4)
            return 0;
        int mod = (int)1e9 + 7;
        long total = pow(26, n, mod);
        long sub = 0;
        // l = 0
        sub = (sub + pow(25, n, mod)) % mod;
        // t == 0
        sub = (sub + pow(25, n, mod)) % mod;
        // e == 1, e == 0
        sub = (sub + (pow(25, n, mod) + pow(25, n - 1, mod) * n) % mod) % mod;
        // l == 0, t == 0
        sub = (mod + sub - pow(24, n, mod)) % mod;
        // l == 0, e < 2
        sub = (mod + sub - (pow(24, n, mod) + pow(24, n - 1, mod) * n) % mod) % mod;
        // t == 0, e < 2
        sub = (mod + sub - (pow(24, n, mod) + pow(24, n - 1, mod) * n) % mod) % mod;
        // t == 0 l == 0, e < 2
        sub = (sub + (pow(23, n, mod) + pow(23, n - 1, mod) * n) % mod) % mod;
        return (int)((mod + total - sub) % mod);
    }
    
    long pow(int n, int p, int mod)  {
        long ans = 1;
        long cur = n;
        for (int i = 0; i < 32; i++)    {
            if ((p & (1 << i)) != 0)
                ans = (ans * cur) % mod;
            cur = (cur * cur) % mod;
        }
        return ans;
    }
}