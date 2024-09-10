class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        Map<Integer, Integer> counter = new HashMap<>();
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> a[0] == b[0] ? 0 : (a[0] > b[0] ? 1 : -1)); // [finishTime, roomId]
        PriorityQueue<Integer> idle = new PriorityQueue<>((a, b) -> a - b);
        for (int i = 0; i < n; i++)
            idle.offer(i);
        long time = 0;
        for (int i = 0; i < meetings.length; i++)   {
            time = Math.max(time, meetings[i][0]);
            if (idle.isEmpty() && !busy.isEmpty() && time < busy.peek()[0])
                time = busy.peek()[0];
            while (!busy.isEmpty() && busy.peek()[0] <= time)   {
                long[] finish = busy.poll();
                idle.offer((int)finish[1]);
            }
            int roomToAssign = idle.poll();
            counter.put(roomToAssign, counter.getOrDefault(roomToAssign, 0) + 1);
            busy.offer(new long[] {time + meetings[i][1] - meetings[i][0], roomToAssign});
        }
        int ans = -1;
        int maxCount = -1;
        for (int i = 0; i < n; i++) {
            if (counter.getOrDefault(i, 0) > maxCount)  {
                maxCount = counter.getOrDefault(i, 0);
                ans = i;
            }
        }
        
        return ans;
    }
}