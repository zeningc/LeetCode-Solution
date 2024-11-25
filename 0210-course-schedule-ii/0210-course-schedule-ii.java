class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        int idx = 0;
        List<Integer>[] map = new List[numCourses];
        int[] indegree = new int[numCourses];
        for (int[] p : prerequisites)   {
            if (map[p[1]] == null)
                map[p[1]] = new ArrayList<>();
            map[p[1]].add(p[0]);
            indegree[p[0]]++;
        }
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0)
                q.offer(i);
        
        while (!q.isEmpty())    {
            int u = q.poll();
            ans[idx++] = u;
            if (map[u] == null)
                continue;
            for (int v : map[u])   {
                indegree[v]--;
                if (indegree[v] == 0)
                    q.offer(v);
            }
        }
        
        return idx == numCourses ? ans : new int[0]; 
    }
}