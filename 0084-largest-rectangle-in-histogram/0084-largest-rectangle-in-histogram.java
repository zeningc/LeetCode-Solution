class Solution {
    public int largestRectangleArea(int[] heights) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= heights.length; i++)    {
            while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] >= heights[i]))  {
                int pop = stack.pop();
                int pre = stack.isEmpty() ? -1 : stack.peek();  
                ans = Math.max(ans, heights[pop] * (i - pre - 1));
            }
            stack.push(i);
        }
        
        return ans;
    }
}