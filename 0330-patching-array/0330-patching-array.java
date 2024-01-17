class Solution {
    public int minPatches(int[] nums, int n) {
        long r = 0;
        int ans = 0;
        for (int num : nums)    {
            while (num > r + 1 && r < n)    {
                ans++;
                r += (r + 1);
            }
            
            
            if (num <= r + 1)   {
                r += num;
            }
            
            if (r >= n)
                break;
        }
        
        while (r < n)   {
            ans++;
            r += (r + 1);
        }
        
        return ans;
    }
}