class Solution {
    public int minimumDifference(int[] nums, int k) {
        int[][] presum = new int[nums.length + 1][32];
        for (int i = 0; i < nums.length; i++)   {
            for (int j = 0; j < 32; j++)    {
                presum[i + 1][j] = presum[i][j] + ((nums[i] & (1 << j)) != 0 ? 1 : 0);
            }
        }
        int cur = Integer.MAX_VALUE;
        int lo = 0;
        int ans = Integer.MAX_VALUE;
        for (int hi = 0; hi < nums.length; hi++)    {
            cur &= nums[hi];
            while (cur < k && lo < hi)    {
                ans = Math.min(ans, Math.abs(k - cur));
                cur = getNum(presum, lo + 1, hi);
                lo++;
            }
            ans = Math.min(ans, Math.abs(k - cur));
        }
        
        return ans;
    }
    
    int getNum(int[][] presum, int lo, int hi)  {
        int ans = 0;
        for (int i = 0; i < 32; i++)    {
            if (presum[hi + 1][i] - presum[lo][i] == hi - lo + 1)
                ans |= (1 << i);
        }
        return ans;
    }
}