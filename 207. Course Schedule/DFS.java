class Solution {
    int[] visited;
    Map<Integer, List<Integer>> graph;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        graph = new HashMap<>();
        visited = new int[numCourses];
        for (int[] prereq : prerequisites)  {
            int u = prereq[1];
            int v = prereq[0];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            graph.get(u).add(v);
        }
        
        for (int i = 0; i < numCourses; i++)    {
            if (!dfs(i))
                return false;
        }
        
        return true;
    }
    
    boolean dfs(int node)   {
        if (visited[node] == 1)
            return false;
        if (visited[node] == 2)
            return true;
        
        visited[node] = 1;
        
        if (!graph.containsKey(node))   {
            visited[node] = 2;
            return true;
        }
        
        for (int v : graph.get(node))   {
            if (!dfs(v))
                return false;
        }
        
        visited[node] = 2;
        
        return true;
    }
}
