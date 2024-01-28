class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int cnt = 0;
        int far = Integer.MIN_VALUE;
        for (int[] interval : intervals)    {
            if (interval[0] >= far) {
                far = interval[1];
                continue;
            }
            if (far < interval[1])   {
                far = interval[1];
            }
            else    {
                cnt++;
            }
            
            
        }
        
        return intervals.length - cnt;
    }
}