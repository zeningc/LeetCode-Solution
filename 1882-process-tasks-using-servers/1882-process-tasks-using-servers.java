class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < servers.length; i++)
            idle.offer(new int[] {i, servers[i]});
        
        PriorityQueue<int[]> work = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int time = 0;
        int[] ans = new int[tasks.length];
        for (int i = 0; i < tasks.length; i++)  {
            time = Math.max(time, i);
            if (idle.isEmpty() && work.peek()[1] > time)
                time = work.peek()[1];
            while (!work.isEmpty() && work.peek()[1] <= time)   {
                int[] server = work.poll();
                idle.offer(new int[] {server[0], servers[server[0]]});
            }
            
            int[] server = idle.poll();
            ans[i] = server[0];
            
            work.offer(new int[] {server[0], time + tasks[i]});
        }
        
        return ans;
    }
}