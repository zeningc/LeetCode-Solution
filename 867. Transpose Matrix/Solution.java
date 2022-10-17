class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (n == m) {
            for (int i = 0; i < m; i++) {
                for (int j = i; j < n; j++) {
                    int t = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = t;
                }
            }

            return matrix;
        }
        
        int[][] ret = new int[n][m];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ret[j][i] = matrix[i][j];
            }
        }
        
        return ret;
    }
}
