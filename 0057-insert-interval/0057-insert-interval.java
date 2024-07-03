class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        for (int[] interval : intervals)    {
            if (interval[1] < newInterval[0])   {
                ans.add(interval);
                continue;
            }
            if (interval[0] > end)    {
                if (start != -1)    {
                    ans.add(new int[] {start, end});
                    start = -1;
                }
                ans.add(interval);
                continue;
            }
            start = Math.min(interval[0], start);
            end = Math.max(interval[1], end);
        }
        if (start != -1)
            ans.add(new int[] {start, end});
        
        return ans.toArray(new int[ans.size()][2]);
    }
}