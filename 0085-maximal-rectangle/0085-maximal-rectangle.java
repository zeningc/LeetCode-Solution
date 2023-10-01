class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] h = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++)
        {
            for (int j = 0; j < n; j++)
            {
                h[j] = matrix[i][j] == '0' ? 0 : h[j] + 1;
            }
            
            Deque<Integer> stack = new LinkedList<>();
            for (int j = 0; j <= n; j++)
            {
                while (!stack.isEmpty() && (j == n || h[stack.peek()] > h[j]))
                {
                    int pop = stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    ans = Math.max(ans, h[pop] * (j - left - 1));
                }
                stack.push(j);
            }
        }
        
        
        return ans;
    }
}