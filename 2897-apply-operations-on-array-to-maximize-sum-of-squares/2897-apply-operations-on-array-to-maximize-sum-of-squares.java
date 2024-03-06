class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long ans = 0;
        int mod = (int)1e9 + 7;
        int[] freq = new int[32];
        for (int num : nums)    {
            for (int i = 0; i < 32; i++)    {
                if ((num & (1 << i)) != 0)
                    freq[i]++;
            }
        }
        
        
        for (int i = 0; i < k; i++) {
            int num = 0;
            for (int j = 0; j < 32; j++)    {
                if (freq[j] > 0)    {
                    freq[j]--;
                    num |= (1 << j);
                }
            }
            ans = (ans + (long)num * num) % mod;
        }
        
        return (int)(ans % mod);
    }
}