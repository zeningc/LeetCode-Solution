class Solution {
    public int[][] seePeople(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] ans = new int[m][n];
        
        for (int i = 0; i < m; i++)
        {
            Deque<Integer> stack = new LinkedList<>();
            for (int j = 0; j < n; j++)
            {
                int nxt = j;
                while (!stack.isEmpty() && heights[i][j] > heights[i][stack.peek()])
                {
                    int cur = stack.pop();
                    if (heights[i][nxt] != heights[i][cur])
                        ans[i][cur]++; 
                    nxt = cur;
                }
                if (!stack.isEmpty())
                    ans[i][stack.peek()]++;
                stack.push(j);
            }
        }
        
        for (int j = 0; j < n; j++) {
            Deque<Integer> stack = new LinkedList<>();
            for (int i = 0; i < m; i++)
            {
                int nxt = i;
                while (!stack.isEmpty() && heights[i][j] > heights[stack.peek()][j])
                {
                    int cur = stack.pop();
                    if (heights[nxt][j] != heights[cur][j])
                        ans[cur][j]++; 
                    nxt = cur;
                }
                if (!stack.isEmpty())
                    ans[stack.peek()][j]++;
                stack.push(i);
            }
        }
        
        return ans;
    }
}