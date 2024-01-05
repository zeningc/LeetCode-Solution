class Solution {
    public int longestNiceSubarray(int[] nums) {
        int[] freq = new int[33];
        int l = 0;
        int ans = 1;
        for (int r = 0; r < nums.length; r++)   {
            for (int i = 0; i < 32; i++)    {
                if ((nums[r] & (1 << i)) != 0)
                    freq[i]++;
            }
            
            while (!check(freq))    {
                for (int i = 0; i < 32; i++)    {
                    if ((nums[l] & (1 << i)) != 0)
                        freq[i]--;
                }
                l++;
            }
            
            ans = Math.max(ans, r - l + 1);
        }
        
        return ans;
    }
    
    boolean check(int[] freq)   {
        for (int f : freq)  {
            if (f > 1)
                return false;
        }
        
        return true;
    }
}