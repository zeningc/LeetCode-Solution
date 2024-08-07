class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int lo = 0;
        int hi = n - 1;
        int loMax = 0;
        int hiMax = 0;
        int ans = 0;
        while (lo < hi)    {
            if (height[lo] < height[hi])    {
                if (height[lo] > loMax)
                    loMax = height[lo];
                else
                    ans += loMax - height[lo];
                lo++;
            }
            else    {
                if (height[hi] > hiMax)
                    hiMax = height[hi];
                else
                    ans += hiMax - height[hi];
                hi--;
            }
        }
        
        return ans;
    }
}
