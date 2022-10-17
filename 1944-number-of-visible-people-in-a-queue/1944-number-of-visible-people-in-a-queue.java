class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--)    {
            int cnt = 0;
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop();
                cnt++;
            }
            if (!stack.isEmpty())
                cnt++;
            ans[i] = cnt;
            stack.push(i);
        }
        return ans;
    }
}



    