class Solution {
    public int findMinArrowShots(int[][] points) {
        int cnt = 0;
        Arrays.sort(points, (a, b) -> a[1] == b[1] ? 0 : (a[1] > b[1] ? 1 : -1));
        long r = Long.MIN_VALUE;
        for (int[] point : points)  {
            if (point[0] <= r)
                cnt++;
            else if (point[1] > r)
                r = point[1];
        }
        
        return points.length - cnt;
    }
}