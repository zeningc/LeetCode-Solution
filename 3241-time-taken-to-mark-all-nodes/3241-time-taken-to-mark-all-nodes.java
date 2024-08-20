class Solution {
    Map<Integer, List<Integer>> graph;
    int[] ans;
    int[] subTime;
    public int[] timeTaken(int[][] edges) {
        int n = edges.length + 1;
        ans = new int[n];
        subTime = new int[n];
        graph = new HashMap<>();
        for (int[] edge : edges)    {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }
        dfs(0, -1);
        dfs1(0, -1, 0);
        
        return ans;
    }
    
    int dfs(int u, int p)   {
        int maxSub = 0;
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            maxSub = Math.max(maxSub, dfs(v, u) + (v % 2 == 0 ? 2 : 1));
        }
        subTime[u] = maxSub;
        return subTime[u];
    }
    
    void dfs1(int u, int p, int t)   {
        int curTime1 = t;
        int curTime2 = -1;
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            int val = subTime[v] + (v % 2 == 0 ? 2 : 1);
            if (curTime1 <= val)    {
                curTime2 = curTime1;
                curTime1 = val;
            }
            else if (curTime2 <= val)   {
                curTime2 = val;
            }
        }
        
        for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
            if (v == p)
                continue;
            int nxt = 0;
            if (subTime[v] + (v % 2 == 0 ? 2 : 1) == curTime1)  {
                nxt = curTime2;
            }
            else    {
                nxt = curTime1;
            }
            dfs1(v, u, nxt + (u % 2 == 0 ? 2 : 1));
        }
        
        ans[u] = curTime1;
    }
}