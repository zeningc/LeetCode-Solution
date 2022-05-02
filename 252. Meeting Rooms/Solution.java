class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        List<int[]> list = new ArrayList<>(intervals.length * 2);
        for (int[] interval : intervals)    {
            list.add(new int[] {interval[0], 1});
            list.add(new int[] {interval[1], -1});
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int cnt = 0;
        for (int[] arr : list)  {
            cnt += arr[1];
            if (cnt > 1)
                return false;
        }
        return true;
    }
}
