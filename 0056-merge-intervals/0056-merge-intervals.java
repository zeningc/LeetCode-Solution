class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> intervalList = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = -1;
        int end = -1;
        
        for (int[] interval : intervals)    {
            if (interval[0] > end)  {
                if (end != -1)
                    intervalList.add(new int[] {start, end});
                start = interval[0];
                end = interval[1];
                continue;
            }
            
            end = Math.max(end, interval[1]);
        }
        if (end != -1)
            intervalList.add(new int[] {start, end});
        return intervalList.toArray(new int[intervalList.size()][2]);
    }
}