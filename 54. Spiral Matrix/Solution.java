class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] dir = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = matrix.length;
        int n = matrix[0].length;
        int left = -1;
        int right = n;
        int up = -1;
        int down = m;
        
        int x = 0;
        int y = 0;
        
        int step = 1;
        int dirIdx = 0;
        List<Integer> list = new LinkedList<>();
        list.add(matrix[0][0]);
        while (step < m * n)    {
            int nx = x + dir[dirIdx][0];
            int ny = y + dir[dirIdx][1];
            
            if (nx <= up || nx >= down || ny <= left || ny >= right)    {
                switch(dirIdx)  {
                    case 0:
                        up = x;
                        break;
                    case 1:
                        right = y;
                        break;
                    case 2:
                        down = x;
                        break;
                    case 3:
                        left = y;
                        break;
                    default:
                        break;
                }
                dirIdx = (dirIdx + 1) % 4;
                continue;
            }
            list.add(matrix[nx][ny]);
            step++;
            x = nx;
            y = ny;
        }
        
        return list;
    }
}
