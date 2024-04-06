class Solution {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> row = new LinkedList<>();
        List<Integer> col = new LinkedList<>();
        for (int i = 0; i < m; i++)   {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)    {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        
        Collections.sort(row);
        Collections.sort(col);
        
        int midRow = row.get(row.size() / 2);
        int midCol = col.get(col.size() / 2);
        
        int rowDis = 0;
        int colDis = 0;
        
        for (int r : row)   {
            rowDis += Math.abs(midRow - r);
        }
        for (int c : col)   {
            colDis += Math.abs(midCol - c);
        }
        
        return rowDis + colDis;
    }
}