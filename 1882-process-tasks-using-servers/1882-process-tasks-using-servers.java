class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        PriorityQueue<Integer> idle = new PriorityQueue<>((a, b) -> servers[a] == servers[b] ? a - b : servers[a] - servers[b]);
        PriorityQueue<int[]> running = new PriorityQueue<>((a, b) -> a[0] - b[0]); // [finish time, server idx]
        for (int i = 0; i < n; i++)
            idle.offer(i);
        int time = 0;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);
            
            if (idle.isEmpty() && !running.isEmpty())
                time = running.peek()[0];
            while (!running.isEmpty() && running.peek()[0] <= time) {
                int[] finish = running.poll();
                idle.offer(finish[1]);
            }
            
            ans[i] = idle.peek();
            running.offer(new int[] {time + tasks[i], idle.peek()});
            idle.poll();
        }
    
        return ans;
    }
}