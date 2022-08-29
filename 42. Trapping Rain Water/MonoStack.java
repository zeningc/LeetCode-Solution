class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i])    {
                int val = height[stack.pop()];
                if (!stack.isEmpty())   {
                    ans += (Math.min(height[stack.peek()], height[i]) - val) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        
        return ans;
    }
}
