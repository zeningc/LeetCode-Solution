class Solution {
    Map<Integer, List<Integer>> graph;
    int[][] bombs;
    public int maximumDetonation(int[][] bombs) {
        graph = new HashMap<>();
        this.bombs = bombs;
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n;  j++)    {
                if (i == j)
                    continue;
                int x1 = bombs[i][0];
                int x2 = bombs[j][0];
                int y1 = bombs[i][1];
                int y2 = bombs[j][1];
                int r1 = bombs[i][2];
                long distSq = (long)(x1 - x2) * (x1 - x2) + (long)(y1 - y2) * (y1 - y2);
                if (distSq <= (long)r1 * r1)  {
                    if (!graph.containsKey(i))
                        graph.put(i, new LinkedList<>());
                    graph.get(i).add(j);
                }
            }
        }
        int ans = 1;
        for (int i = 0; i < n; i++)
            ans = Math.max(ans, cntBombs(i));
        return ans;
    }
    
    int cntBombs(int idx)    {
        boolean[] visited = new boolean[bombs.length];
        Deque<Integer> q = new LinkedList<>();
        q.offer(idx);
        int cnt = 0;
        while (!q.isEmpty())    {
            int curr = q.poll();
            if (visited[curr])
                continue;
            cnt++;
            visited[curr] = true;
            if (!graph.containsKey(curr))
                continue;
            for (int v : graph.get(curr))   {
                if (visited[v])
                    continue;
                q.offer(v);
            }
        }
        
        return cnt;
    }
}
