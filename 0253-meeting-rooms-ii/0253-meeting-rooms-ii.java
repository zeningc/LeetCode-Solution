class Solution {
    public int minMeetingRooms(int[][] intervals) {
        int ans = 0;
        int n = intervals.length;
        int[] startTime = new int[n];
        int[] endTime = new int[n];
    
        for (int i = 0; i < n; i++) {
            startTime[i] = intervals[i][0];
            endTime[i] = intervals[i][1];
        }
        
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        
        int end = 0;
        for (int start = 0; start < n; start++) {
            if (startTime[start] < endTime[end])
                ans++;
            else
                end++;
        }
        
        return ans;
    }
}