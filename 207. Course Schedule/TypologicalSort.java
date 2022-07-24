class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] prereq : prerequisites)  {
            int u = prereq[1];
            int v = prereq[0];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            graph.get(u).add(v);
            inDegree[v]++;
        }
        
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)    {
            if (inDegree[i] == 0)
                q.add(i);
        }
        int cnt = 0;
        
        while (!q.isEmpty())    {
            int u = q.poll();
            cnt++;
            if (!graph.containsKey(u))
                continue;
            for (int v : graph.get(u))  {
                inDegree[v]--;
                if (inDegree[v] == 0)
                    q.push(v);
            }
        }
        
        return cnt == numCourses;
    }
}
