class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        boolean ans = true;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            int val = matrix[0][j];
            int x = 0;
            int y = j;
            while (x < m && y < n)  {
                if (matrix[x][y] != val)
                    return false;
                x++;
                y++;
            }
        }
        
        for (int i = 1; i < m; i++) {
            int val = matrix[i][0];
            int x = i;
            int y = 0;
            while (x < m && y < n)  {
                if (matrix[x][y] != val)
                    return false;
                x++;
                y++;
            }
        }
        
        return true;
    }
}
