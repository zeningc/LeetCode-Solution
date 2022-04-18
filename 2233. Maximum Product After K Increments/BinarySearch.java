class Solution {
    int[] nums;
    int k;
    long[] prefix;
    public int maximumProduct(int[] nums, int k) {
        int mod = (int)1e9 + 7;
        this.nums = nums;
        this.k = k;
        int n = nums.length;
        Arrays.sort(nums);
        prefix = new long[n + 1];
        for (int i = 0; i < n; i++)
            prefix[i + 1] = prefix[i] + nums[i];
        
        int left = 0;
        int right = n - 1;
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (check(mid))
                left = mid + 1;
            else
                right = mid - 1;
        }
        k -= (long)(right + 1) * nums[right] -  prefix[right + 1];
        int level = (nums[right] + k / (right + 1)) % mod;
        k %= (right + 1);
        long ans = 1;
        for (int i = 0; i < n; i++) {
            if (i < k)
                ans = (ans * (level + 1)) % mod;
            else if (i <= right)
                ans = (ans * level) % mod;
            else
                ans = (ans * nums[i]) % mod;
        }
        
        return (int)ans;
    }
    
    boolean check(int mid) {
        return (long)nums[mid] * (mid + 1) - prefix[mid + 1] < (long)k;
    }
}