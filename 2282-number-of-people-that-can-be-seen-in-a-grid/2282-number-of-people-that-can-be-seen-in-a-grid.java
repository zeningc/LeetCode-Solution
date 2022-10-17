class Solution {
    public int[][] seePeople(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] ans = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            Deque<Integer> stack = new LinkedList<>();
            for (int j = n - 1; j >= 0; j--) {
                int cnt = 0;
                int prev = -1;
                while (!stack.isEmpty() && heights[i][j] > heights[i][stack.peek()])   {
                    if (prev != heights[i][stack.peek()])
                        cnt++;
                    prev = heights[i][stack.pop()];
                }
                if (!stack.isEmpty())
                    cnt++;
                ans[i][j] += cnt;
                stack.push(j);
            }
        }
        
        for (int j = 0; j < n; j++) {
            Deque<Integer> stack = new LinkedList<>();
            for (int i = m - 1; i >= 0; i--) {
                int cnt = 0;
                int prev = -1;
                while (!stack.isEmpty() && heights[i][j] > heights[stack.peek()][j])   {
                    if (prev != heights[stack.peek()][j])
                        cnt++;
                    prev = heights[stack.pop()][j];
                }
                if (!stack.isEmpty())
                    cnt++;
                ans[i][j] += cnt;
                stack.push(i);
            }
        }
        
        return ans;
    }
}