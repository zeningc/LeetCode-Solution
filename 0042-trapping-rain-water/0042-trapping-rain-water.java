class Solution {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i])    {
                int pop = stack.pop();
                if (!stack.isEmpty())   {
                    ans += (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - height[pop]);
                }
            }
            stack.push(i);
        }
        
        return ans;
    }
}