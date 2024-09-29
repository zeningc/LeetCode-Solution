class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        int start = -1;
        int end = -1;
        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals)    {
            if (start == -1)    {
                start = interval[0];
                end = interval[1];
                continue;
            }
            
            if (end < interval[0])  {
                ans.add(new int[] {start, end});
                start = interval[0];
                end = interval[1];
                continue;
            }
            
            end = Math.max(end, interval[1]);
        }
        
        ans.add(new int[] {start, end});
        
        int[][] ansArr = new int[ans.size()][2];
        int idx = 0;
        for (int[] a : ans)
            ansArr[idx++] = a;
        return ansArr;
    }
}