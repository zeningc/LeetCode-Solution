class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        PriorityQueue<int[]> leaveQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> arriveQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> empty = new PriorityQueue<>();
        int[] seat = new int[times.length];
        for (int i = 0; i < times.length; i++)  {
            leaveQ.offer(new int[] {times[i][1], i});
            arriveQ.offer(new int[] {times[i][0], i});
        }
        int ans = 0;
        int cnt = 0;
        for (int i = 0; !arriveQ.isEmpty(); i++)  {
            while (!leaveQ.isEmpty() && leaveQ.peek()[0] == i)
                empty.offer(seat[leaveQ.poll()[1]]);
            if (!arriveQ.isEmpty() && arriveQ.peek()[0] == i)  {
                int p = arriveQ.poll()[1];
                if (empty.isEmpty())    {
                    seat[p] = cnt++;
                }
                else    {
                    seat[p] = empty.poll();
                }
                if (p == targetFriend)
                    return seat[p];
            }
        }
        
        return -1;
    }
}