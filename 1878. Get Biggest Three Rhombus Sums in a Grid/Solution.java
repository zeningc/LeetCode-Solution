class Solution {
    int m;
    int n;
    public int[] getBiggestThree(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        TreeSet<Integer> set = new TreeSet<>();
        int[][] prefix1 = new int[m + 1][n + 1];
        int[][] prefix2 = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefix1[i + 1][j + 1] = prefix1[i][j] + grid[i][j];
                set.add(grid[i][j]);
            }
        }
        
        for (int i = 0; i < m; i++)
            for (int j = n - 1; j >= 0; j--)
                prefix2[i + 1][j] = prefix2[i][j + 1] + grid[i][j];
        
        for (int size = 2; size <= Math.min(m, n); size++)  {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int rightX = i + size - 1;
                    int rightY = j + size - 1;
                    int bottomX = rightX + size - 1;
                    int bottomY = rightY - size + 1;
                    int leftX = i + size - 1;
                    int leftY = j - size + 1;
                    
                    if (!check(rightX, rightY) || !check(leftX, leftY) || !check(bottomX, bottomY))
                        continue;
                    int curr = 0;
                    curr += prefix1[rightX+1][rightY+1] + prefix1[bottomX+1][bottomY+1] - prefix1[i][j] - prefix1[leftX][leftY];
                    curr += prefix2[leftX + 1][leftY] + prefix2[bottomX + 1][bottomY] - prefix2[i][j + 1] - prefix2[rightX][rightY + 1];
                    curr -= grid[i][j];
                    curr -= grid[leftX][leftY];
                    curr -= grid[rightX][rightY];
                    curr -= grid[bottomX][bottomY];
                    set.add(curr);
                }
            }
        }
        
        int[] ans = new int[Math.min(3, set.size())];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = set.pollLast();
        }
        
        return ans;
    }
    
    boolean check(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
    
}
