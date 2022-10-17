class Solution {
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> list = new LinkedList<>();
        
        for (int[] interval : intervals)    {
            list.add(new int[] {interval[0], 1});
            list.add(new int[] {interval[1], -1});
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        int cnt = 0;
        int ans = 1;
        
        for (int[] curr : list) {
            cnt += curr[1];
            ans = Math.max(ans, cnt);
        }
        
        return ans;
    }
}