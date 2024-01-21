class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int ans = 0;
        int i = 0;
        int minReach = Integer.MIN_VALUE;
        for (int[] interval : intervals)    {
            int a = interval[0];
            int b = interval[1];
            if (a < minReach)
                ans++;
            else
                minReach = Math.max(minReach, b);
        }
        
        return ans;
    }
}