class Solution {
    long[] factor;
    long[] power;
    int mod = (int)1e9 + 7;
    public int numberOfSequence(int n, int[] sick) {
        List<Integer> grps = new ArrayList<>();
        factor = new long[n + 1];
        power = new long[n + 1];
        factor[0] = 1;
        power[0] = 1;
        for (int i = 1; i <= n; i++)    {
            power[i] = (power[i - 1] * 2) % mod;
            factor[i] = (factor[i - 1] * i) % mod;
        }
        
        int sum = 0;
        for (int i = 0; i < sick.length; i++)   {
            int grp = i == 0 ? sick[i] : sick[i] - sick[i - 1] - 1;
            sum += grp;
            grps.add(grp);
            if (i == sick.length - 1)   {
                sum += n - sick[i] - 1;
                grps.add(n - sick[i] - 1);
            }
        }
        
        long ans = factor[sum];
        for (int grp : grps)
            ans = (ans * inv(factor[grp])) % mod;
        
        for (int i = 1; i < grps.size() - 1; i++)   {
            ans = (ans * power[Math.max(0, grps.get(i) - 1)]) % mod;
        }
        
        return (int)(ans % mod);
    }
    long inv(long x) {
        return quickPow(x, mod - 2);
    }
    
    long quickPow(long x, long y)  {
        long cur = x;
        long ans = 1;
        for (int i = 0; i < 32; i++)    {
            if ((y & (1 << i)) != 0)
                ans = (ans * cur) % mod;
            cur = (cur * cur) % mod;
        }
        return ans;
    }
}