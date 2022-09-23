class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;
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
        int ptr = 0;
        int[] ans = new int[numCourses];
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0)
                q.offer(i);
        
        while (!q.isEmpty())    {
            int u = q.poll();
            ans[ptr++] = u;
            if (!graph.containsKey(u))
                continue;
            for (int v : graph.get(u))  {
                inDegree[v]--;
                if (inDegree[v] == 0)
                    q.offer(v);
            }
        }
        
        return ptr == numCourses ? ans : new int[0];
    }
}