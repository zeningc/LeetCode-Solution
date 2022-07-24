class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int lo = intervals[0][0];
        int hi = intervals[0][1];
        List<int[]> list = new LinkedList<>();
        for (int i = 1; i <= intervals.length; i++)  {
            if (i < intervals.length && hi >= intervals[i][0] && lo <= intervals[i][1]) {
                lo = Math.min(lo, intervals[i][0]);
                hi = Math.max(hi, intervals[i][1]);
            }
            else    {
                list.add(new int[] {lo, hi});
                if (i < intervals.length)   {
                    lo = intervals[i][0];
                    hi = intervals[i][1];
                }
            }
        }
        
        return list.toArray(new int[list.size()][2]);
    }
}
