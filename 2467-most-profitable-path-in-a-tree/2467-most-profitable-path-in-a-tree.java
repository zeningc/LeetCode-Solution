class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            edgeMap.computeIfAbsent(u, x -> new LinkedList<>()).add(v);
            edgeMap.computeIfAbsent(v, x -> new LinkedList<>()).add(u);
        }
        int[] parents = new int[n];
        findParent(edgeMap, parents, 0, -1);
        
        int[] bobArriveTime = new int[n];
        Arrays.fill(bobArriveTime, Integer.MAX_VALUE);
        int p = bob;
        int t = 0;
        while (p != -1) {
            bobArriveTime[p] = t++;
            p = parents[p];
        }
        
        int ans = Integer.MIN_VALUE;
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        t = 0;
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int[] cur = q.poll();
                int u = cur[0];
                int val = cur[1];
                
                if (bobArriveTime[u] == t)
                    val += amount[u] / 2;
                else if (bobArriveTime[u] > t)
                    val += amount[u];
                
                if (edgeMap.get(u).size() == 1 && edgeMap.get(u).get(0) == parents[u]) {
                    ans = Math.max(ans, val);
                    continue;
                }
                
                for (int v : edgeMap.get(u))    {
                    if (v == parents[u])
                        continue;
                    q.offer(new int[] {v, val});
                }
            }
            t++;
        }
        
        return ans;
    }
    
    void findParent(Map<Integer, List<Integer>> edgeMap, int[] parents, int u, int p) {
        parents[u] = p;
        
        for (int v : edgeMap.get(u))    {
            if (v == p)
                continue;
            findParent(edgeMap, parents, v, u);
        }
    }
    
    
}