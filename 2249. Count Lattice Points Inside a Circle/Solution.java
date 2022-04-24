class Solution {
    public int countLatticePoints(int[][] circles) {
        int ans = 0;
        for (int i = -199; i <= 300; i++)   {
            for (int j = -199; j <= 300; j++)   {
                for (int k = 0; k < circles.length; k++)    {
                    int x = circles[k][0];
                    int y = circles[k][1];
                    int r = circles[k][2];
                    if ((i - x) * (i - x) + (j - y) * (j - y) <= r * r) {
                        ans++;
                        break;
                    }
                }
            }
        }
        
        return ans;
    }
}
