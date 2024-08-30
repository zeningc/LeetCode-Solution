class Solution {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int mod = 1000000007;
        long[] power = new long[nums.length + 1];
        power[0] = 1;
        for (int i = 1; i <= nums.length; i++)
            power[i] = (power[i - 1] * 2) % mod;
        int q = nums.length - 1;
        long ans = 0;
        for (int p = 0; p <= q; p++)    {
            if (nums[p] > target)
                break;
            int t = target - nums[p];
            while (q >= p && nums[p] + nums[q] > target)
                q--;
            if (q < p)
                break;
            int len = q - p;
            ans = (ans + power[len]) % mod;
        }
        
        return (int)ans;
    }
}