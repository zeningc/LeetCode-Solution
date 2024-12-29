class Solution {
    public long numberOfSubsequences(int[] nums) {
        int n = nums.length;
        Map<String, Long> cnt = new HashMap<>();
        long ans = 0;
        for (int r = 4; r <= n - 3; r++)    {
            int q = r - 2;
            for (int p = 0; p <= q - 2; p++)    {
                long gcd = getGCD(nums[p], nums[q]);
                String key = nums[p] / gcd + "#" + nums[q] / gcd;
                cnt.put(key, cnt.getOrDefault(key, 0L) + 1);
            }
            
            for (int s = r + 2; s < n; s++) {
                long gcd = getGCD(nums[s], nums[r]);
                String key = nums[s] / gcd + "#" + nums[r] / gcd;
                ans += cnt.getOrDefault(key, 0L);
            }
        }
        
        return ans;
    }
    
    long getGCD(long a, long b)    {
        if (a > b)
            return getGCD(b, a);
        if (a == 0)
            return b;
        return getGCD(b % a, a);
    }
}