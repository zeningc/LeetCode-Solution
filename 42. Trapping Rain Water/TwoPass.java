// it is actually 3 pass but can be optimized to two pass

class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] nextMax = new int[n];
        int[] prevMax = new int[n];
        
        for (int i = n - 2; i >= 0; i--)    
            nextMax[i] = Math.max(nextMax[i + 1], height[i + 1]);
        
        for (int i = 1; i < n; i++)
            prevMax[i] = Math.max(prevMax[i - 1], height[i - 1]);
        
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int bound = Math.min(nextMax[i], prevMax[i]);
            if (height[i] < bound)
                ans += bound - height[i];
        }
        
        return ans;
    }
}
