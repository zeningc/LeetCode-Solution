class Solution {
    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new LinkedList<>();
        int n = heights.length;
        int ans = 0;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || heights[i] <= heights[stack.peek()]))  {
                int pop = stack.pop();
                ans = Math.max(ans, heights[pop] * (i - (stack.isEmpty() ? -1 : stack.peek()) - 1));
            }
            stack.push(i);
        }
        
        return ans;
    }
}