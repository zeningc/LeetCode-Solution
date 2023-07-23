class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        long[] pos = new long[nums.length];
        for (int i = 0; i < nums.length; i++)   {
            if (s.charAt(i) == 'L')
                pos[i] = nums[i] - d;
            else
                pos[i] = nums[i] + d;
        }
        int mod = (int)1e9 + 7;
        Arrays.sort(pos);
        long dist = 0;
        long ret = 0;
        for (int i = 1; i < nums.length; i++)   {
            dist = (dist + i * (pos[i] - pos[i - 1])) % mod;
            ret = (ret + dist) % mod;
        }
        return (int)(ret % mod);
    }
}