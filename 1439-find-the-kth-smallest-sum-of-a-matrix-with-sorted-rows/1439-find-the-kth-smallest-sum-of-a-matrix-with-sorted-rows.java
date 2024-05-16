class Solution {
    int m;
    int n;
    int count = 0;
    public int kthSmallest(int[][] mat, int k) {
        m = mat.length;
        n = mat[0].length;
        int left = 0;
        int right = 200000;
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (check(mat, mid, k)) {
                right = mid - 1;
            }
            else    {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean check(int[][] mat, int target, int k)   {
        int sum = 0;
        for (int i = 0; i < m; i++)
            sum += mat[i][0];
        count = 1;
        if (sum > target)   
            return false;
        dfs(mat, 0, target, k, sum);
        return count >= k;
    }
    
    private void dfs(int[][] mat, int row, int target, int k, int sum)    {
        if (count >= k)
            return;
        if (row == m)
            return;
        
        for (int i = 0; i < n; i++) {
            if (sum - mat[row][0] + mat[row][i] <= target)  {
                if (i > 0)
                    count++;
                dfs(mat, row + 1, target, k, sum - mat[row][0] + mat[row][i]);
            }
            else    {
                break;
            }
        }
        
    }
}



// x x x x x o o