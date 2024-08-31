class Solution {
    long mod = (long)1e9 + 7;
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] power = new long[n + 2];
        power[0] = 1;
        for (int i = 1; i <= n + 1; i++)
            power[i] = (power[i - 1] * 2) % mod;
        
        int[] newNums = new int[n + 1];
        for (int i = 0; i < n; i++)
            newNums[i + 1] = nums[i];
        nums = newNums;
        
        long[] nPresum = new long[n + 1];
        for (int i = 1; i <= n; i++)
            nPresum[i] = (nPresum[i - 1] + ((power[i] * nums[i]) % mod * nums[i]) % mod) % mod;
        long ans = 0;
        for (int i = 1; i <= n; i++)    {
            long delta = multiple(multiple(nums[i], minus(nPresum[n], nPresum[i])), inv(power[i + 1]));
            delta = add(delta, multiple(nums[i], multiple(nums[i], nums[i])));
            ans = add(ans, delta);
        }
        
        return (int)ans;
    }
    
    long inv(long x)   {
        long s = 1;
        for (; x > 1; x = mod % x)
          s = s * (mod - mod / x) % mod;
        return s;
    }
    
    long add(long a, long b)    {
        return (a + b) % mod;
    }
    
    long minus(long a, long b)  {
        return (mod + a - b) % mod;
    }
    
    long multiple(long a, long b)   {
        return (a % mod * b % mod) % mod;
    }
}


/*
1

2^0* + 2^0 * 

*/