class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i])    {
                int prevIdx = stack.pop();
                if (!stack.isEmpty())
                    ans += (Math.min(height[stack.peek()], height[i]) - height[prevIdx]) * (i - stack.peek() - 1);
            }
            stack.push(i);
        }
        
        return ans;
    }
}
