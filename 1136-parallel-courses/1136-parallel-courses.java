class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        List<Integer>[] map = new List[n + 1];
        int[] indeg = new int[n + 1];
        for (int[] r : relations)   {
            if (map[r[0]] == null)
                map[r[0]] = new ArrayList<>();
            map[r[0]].add(r[1]);
            indeg[r[1]]++;
        }
        
        Deque<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++)
            if (indeg[i] == 0)
                q.offer(i);
        int cnt = 0;
        int ans = 0;
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                cnt++;
                if (map[u] == null)
                    continue;
                for (int v : (List<Integer>)map[u])    {
                    indeg[v]--;
                    if (indeg[v] == 0)
                        q.offer(v);
                }
            }
            ans++;
        }
        
        return cnt == n ? ans : -1;
    }
}