class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] presum = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    presum[i][j] = i > 0 ? presum[i - 1][j] + 1 : 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            Deque<Integer> stack = new LinkedList<>();
            for (int j = 0; j <= n; j++) {
                while (!stack.isEmpty() && (j == n || presum[i][j] <= presum[i][stack.peek()])) {
                    int pop = stack.pop();
                    int prev = stack.isEmpty() ? -1 : stack.peek();
                    ans = Math.max(ans, presum[i][pop] * (j - prev - 1));
                }
                stack.push(j);
            }
        }
        
        return ans;
    }
}