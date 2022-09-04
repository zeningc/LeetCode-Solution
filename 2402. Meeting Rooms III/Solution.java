class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int [] cnt = new int[n];
        long[] endTime = new long[n];
        
        for (int[] meeting : meetings)  {
            long start = meeting[0];
            long end = meeting[1];
            long earliestEndTime = endTime[0];
            int earliestRoom = 0;
            int room = -1;
            long delay = 0;
            for (int i = 0; i < n; i++) {
                if (endTime[i] <= start)    {
                    room = i;
                    break;
                }
                if (endTime[i] < earliestEndTime)   {
                    earliestEndTime = endTime[i];
                    earliestRoom = i;
                }
            }
            
            if (room == -1) {
                room = earliestRoom;
                delay = earliestEndTime - start;
            }
            
            cnt[room]++;
            endTime[room] = delay + end;
        }
        
        int ans = -1;
        int maxCnt = -1;
        
        for (int i = 0; i < n; i++) {
            if (cnt[i] > maxCnt)    {
                maxCnt = cnt[i];
                ans = i;
            }
        }
        
        return ans;
        
    }
}
