class Solution {
    public int maxSubarrays(int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++)
            res &= nums[i];
        
        boolean[] pos = new boolean[32];
        for (int i = 0; i < 32; i++)    
            if ((res & (1 << i)) != 0)
                pos[i] = true;
        
        int ans = 0;
        int pre = Integer.MAX_VALUE;
        boolean valid = true;
        for (int i = 0; i < nums.length; i++)   {
            pre &= nums[i];
            valid = true;
            for (int j = 0; j < 32; j++)    {
                if (pos[j])
                    continue;
                if ((pre & (1 << j)) != 0)  {
                    valid = false;
                    break;
                }
            }
            if (valid)  {
                pre = Integer.MAX_VALUE;
                ans++;
                for (int j = 0; j < 32; j++)    {
                    if (pos[j])
                        pos[j] = false;
                }
            }
            
        }
        
        return ans;
    }
}