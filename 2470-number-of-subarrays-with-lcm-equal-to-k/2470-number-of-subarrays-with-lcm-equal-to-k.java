class Solution {
    public int subarrayLCM(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long l = nums[i];
            if (l == (long)k){
                ans++;
            }
            for (int j = i + 1; j < n && l <= (long)k; j++) {
                l = lcm(l, nums[j]);
                if (l == (long)k){
                    ans++;
                }
            }
        }
        
        return ans;
    }
    
    long gcd(long m, long n)   {
        if (m < n)
            return gcd(n, m);
        if (m % n == 0)
            return n;
        
        return gcd(n, m % n);
    }
    
    long lcm(long a, long b)   {
        return a * b / gcd(a, b);
    }
}