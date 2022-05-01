class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        int ans = 0;
        int n = nums.length;
        Set<String> set = new HashSet<>();
        
        long mod = (long)1e9 + 7;
        long base = (long)1e5 + 7;
        long base2 = (long)1e5;
        
        for (int i = 0; i < n; i++)    {
            int cnt = 0;
            long curr = 0;
            long curr2 = 0;
            for (int j = i; j < n; j++)  {
                if (nums[j] % p == 0)
                    cnt++;
                if (cnt > k)
                    break;
                curr = ((base * curr) % mod + nums[j]) % mod;
                curr2 = ((base2 * curr2) % mod + nums[j]) % mod;
                set.add(curr + " " + curr2);
            }
        }
        
        return set.size();
    }
    
}
