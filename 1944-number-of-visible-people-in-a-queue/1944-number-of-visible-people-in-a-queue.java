class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--)
        {
            int cnt = 0;
            while (!stack.isEmpty() && heights[stack.peek()] < heights[i])
            {
                cnt++;
                stack.pop();
            }
            
            
            ans[i] = cnt + (stack.isEmpty() ? 0 : 1);
            stack.push(i);
        }
        
        return ans;
    }
}