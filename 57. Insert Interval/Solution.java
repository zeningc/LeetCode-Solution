class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new LinkedList<>();
        int n = intervals.length;
        int i = 0;
        for (; i < n; i++)
            if (intervals[i][1] < newInterval[0])
                list.add(intervals[i]);
            else 
                break;
        if (i == n) {
            list.add(newInterval);
            return list.toArray(new int[list.size()][2]);
        }
        int lo = newInterval[0];
        int hi = newInterval[1];
        while (i < n && intervals[i][1] >= lo && intervals[i][0] <= hi)    {
            lo = Math.min(lo, intervals[i][0]);
            hi = Math.max(hi, intervals[i][1]);
            i++;
        }
        
        list.add(new int[] {lo, hi});
        
        for (; i < n; i++)
            list.add(intervals[i]);
        
        return list.toArray(new int[list.size()][2]);
        
    }
}
