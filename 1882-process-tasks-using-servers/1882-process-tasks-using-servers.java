class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        Deque<Integer> taskQueue = new LinkedList<>();
        PriorityQueue<Integer> serverQueue = new PriorityQueue<>((a, b) -> servers[a] == servers[b] ? a - b : servers[a] - servers[b]);
        for (int i = 0; i < n; i++)
            serverQueue.offer(i);

        TreeSet<Integer> timeSet = new TreeSet<>();
        Map<Integer, List<Integer>> serverFreeTime = new HashMap<>();
        for (int i = 0; i < m; i++)
            timeSet.add(i);
        int pre = -1;
        int[] ans = new int[m];
        while (timeSet.higher(pre) != null)   {
            int t = timeSet.higher(pre);
            if (t < m)
                taskQueue.offer(t);
            
            for (int serverToFreeIdx : serverFreeTime.getOrDefault(t, new ArrayList<>()))
                serverQueue.offer(serverToFreeIdx);
            
            while (!taskQueue.isEmpty() && !serverQueue.isEmpty())  {
                int taskIdx = taskQueue.poll();
                int serverIdx = serverQueue.poll();
                ans[taskIdx] = serverIdx;
                serverFreeTime.computeIfAbsent(t + tasks[taskIdx], x -> new ArrayList<>()).add(serverIdx);
                if (!timeSet.contains(t + tasks[taskIdx]))  {
                    timeSet.add(t + tasks[taskIdx]);
                }
            }
            pre = t;
        }
    
        return ans;
    }
}