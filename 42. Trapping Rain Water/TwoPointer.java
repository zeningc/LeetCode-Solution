class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int lo = 0;
        int hi = n - 1;
        int loMax = 0;
        int hiMax = 0;
        int ans = 0;
        while (lo < hi) {
            if (height[lo] < height[hi])    {
                if (height[lo] <= loMax) {
                    ans += loMax - height[lo];
                }
                else    {
                    loMax = height[lo];
                }
                lo++;
            }
            else    {
                if (height[hi] <= hiMax) {
                    ans += hiMax - height[hi];
                }
                else    {
                    hiMax = height[hi];
                }
                hi--;
            }
        }
        
        return ans;
    }
}
