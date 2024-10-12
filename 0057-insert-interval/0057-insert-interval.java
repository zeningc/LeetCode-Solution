class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int p = 0;
        while (p < intervals.length)    {
            int[] cur = intervals[p];
            if (p == 0 && cur[0] > newInterval[1] || p > 0 && intervals[p - 1][1] < newInterval[0] && intervals[p][0] > newInterval[1])  {
                ans.add(new int[] {newInterval[0], newInterval[1]});
            }
            if (cur[1] < newInterval[0] || cur[0] > newInterval[1]) {
                ans.add(cur);
                p++;
                continue;
            }
            int start = Math.min(newInterval[0], cur[0]);
            int end = Math.max(newInterval[1], cur[1]);
            
            while (p < intervals.length && end >= intervals[p][0])  {
                end = Math.max(end, intervals[p][1]);
                p++;
            }
            
            ans.add(new int[] {start, end});
        }
        
        if (intervals.length == 0 || intervals[intervals.length - 1][1] < newInterval[0])
            ans.add(new int[] {newInterval[0], newInterval[1]});
        
        int[][] ret = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++)
            ret[i] = ans.get(i);
        
        return ret;
    }
}