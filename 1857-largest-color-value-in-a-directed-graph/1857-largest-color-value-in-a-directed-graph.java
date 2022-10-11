class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = colors.length();
        char[] belong = colors.toCharArray();
        int[] indegCopy = new int[n];
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u))
                graph.put(u, new LinkedList<>());
            graph.get(u).add(v);
            indegCopy[v]++;
        }
        int ans = -1;
        for (char c = 'a'; c <= 'z'; c++)    {
            Queue<int[]> q = new LinkedList<>();
            int[] indeg = indegCopy.clone();
            int[] nodeVal = new int[n];
            for (int i = 0; i < n; i++) {
                if (indeg[i] == 0){
                    q.offer(new int[] {i, c == belong[i] ? 1 : 0});
                    nodeVal[i] = 1;
                }
            }
            int cnt = 0;
            while (!q.isEmpty())    {
                int[] curr = q.poll();
                int u = curr[0];
                int val = curr[1];
                cnt++;
                if (!graph.containsKey(u))
                    continue;
                for (int v : graph.get(u))  {
                    indeg[v]--;
                    nodeVal[v] = Math.max(nodeVal[v], val + (c == belong[v] ? 1 : 0));
                    if (indeg[v] == 0)  {
                        q.offer(new int[] {v, nodeVal[v]});
                    }
                }
            }
            
            if (cnt != n)
                return -1;
            for (int i = 0; i < n; i++)
                ans = Math.max(ans, nodeVal[i]);
        }
        
        return ans;
    }
}