class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indegree = new int[numCourses];
        
        for (int[] p : prerequisites)   {
            map.computeIfAbsent(p[1], x -> new ArrayList<>()).add(p[0]);
            indegree[p[0]]++;
        }
        
        Deque<Integer> q = new LinkedList<>();
        int cnt = 0;
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                q.offer(i);
        
        while (!q.isEmpty())    {
            int u = q.poll();
            cnt++;
            for (int v : map.getOrDefault(u, new ArrayList<>()))    {
                indegree[v]--;
                if (indegree[v] == 0)
                    q.offer(v);
            }
        }
        
        return cnt == numCourses ? true : false;
    }
}