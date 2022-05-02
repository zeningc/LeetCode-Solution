class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> ansList = new ArrayList<>(intervals.length);
        int prevEnd = -1;
        int prevStart = -1;
        for (int[] interval : intervals)    {
            int start = interval[0];
            int end = interval[1];
            if (prevEnd != -1 && prevEnd < start)   {
                ansList.add(new int[] {prevStart, prevEnd});
                prevStart = start;
                prevEnd = end;
                continue;
            }
            
            prevEnd = Math.max(prevEnd, end);
            prevStart = prevStart == -1 ? start : Math.min(start, prevStart);
        }
        
        ansList.add(new int[] {prevStart, prevEnd});
        return ansList.toArray(new int[ansList.size()][]);
    }
}