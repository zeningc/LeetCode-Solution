class Solution {
    public int numSubmat(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] h = new int[n];
        
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                h[j] = mat[i][j] == 1 ? h[j] + 1 : 0;
            
            Deque<Integer> stack = new LinkedList<>();
            int cnt = 0;
            for (int j = 0; j < n; j++)    {
                while (!stack.isEmpty() && h[stack.peek()] > h[j])  {
                    int pop = stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    cnt -= (pop - left) * (h[pop] - h[j]);
                }
                
                stack.push(j);
                cnt += h[j];
                ans += cnt;
            }
        }
        
        return ans;
    }
}