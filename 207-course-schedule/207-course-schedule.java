class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        
        for (int[] prereq : prerequisites)  {
            int v = prereq[0];
            int u = prereq[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            graph.get(u).add(v);
            inDegree[v]++;
        }
        int cnt = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0)
                q.offer(i);
        
        while (!q.isEmpty())    {
            int u = q.poll();
            cnt++;
            if (!graph.containsKey(u))
                continue;
            for (int v : graph.get(u))  {
                inDegree[v]--;
                if (inDegree[v] == 0)
                    q.offer(v);
            }
        }
        
        
        return cnt == numCourses;
    }
}