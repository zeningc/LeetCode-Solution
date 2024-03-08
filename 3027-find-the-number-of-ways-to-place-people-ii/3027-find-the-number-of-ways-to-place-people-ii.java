class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int n = points.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int upper = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                if (points[j][1] > points[i][1])
                    continue;
                if (upper < points[j][1])
                    ans++;
                upper = Math.max(upper, points[j][1]);
            }
        }
        
        return ans;
    }
}