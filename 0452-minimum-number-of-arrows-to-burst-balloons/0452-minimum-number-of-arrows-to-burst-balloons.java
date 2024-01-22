class Solution {
    public int findMinArrowShots(int[][] points) {
        int cnt = 0;
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? 0 : (a[0] > b[0] ? 1 : -1));
        long r = Long.MAX_VALUE;
        for (int i = points.length - 1; i >= 0; i--)    {
            if (r <= points[i][1])
                cnt++;
            else if (r > points[i][0])
                r = points[i][0];
        }
        
        return points.length - cnt;
    }
}