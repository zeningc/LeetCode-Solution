class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            int d = edge[2];
            graph.computeIfAbsent(u, x -> new ArrayList<>()).add(new int[] {v, d});
            graph.computeIfAbsent(v, x -> new ArrayList<>()).add(new int[] {u, d});
        }
        
        int ans = 0;
        int[] vis = new int[n];
        Arrays.fill(vis, -1);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[] {0, 0});
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int u = cur[0];
            int d = cur[1];
            if (vis[u] != -1)
                continue;
            vis[u] = d;
            ans++;
            if (!graph.containsKey(u))
                continue;
            
            for (int[] nxt : graph.get(u))  {
                int v = nxt[0];
                int nd = nxt[1];
                
                if (vis[v] != -1 || d + nd + 1 > maxMoves)
                    continue;
                
                pq.offer(new int[] {v, d + nd + 1});
            }
        }
        
        for (int i = 0; i < edges.length; i++)  {
            int u = edges[i][0];
            int v = edges[i][1];
            int d = edges[i][2];
            if (vis[u] == -1 && vis[v] == -1)
                continue;
            
            if (vis[u] == -1)   {
                ans += Math.min(maxMoves - vis[v], d);
                continue;
            }
            
            
            if (vis[v] == -1)   {
                ans += Math.min(maxMoves - vis[u], d);
                continue;
            }
            
            
            ans += Math.min(maxMoves - vis[u] + maxMoves - vis[v], d);
        }
        
        return ans;
    }
}

