class Solution {
    long ans = 0;
    int seats;
    Map<Integer, List<Integer>> m;
    public long minimumFuelCost(int[][] roads, int seats) {
        if (roads.length == 0)
            return 0;
        this.seats = seats;
        m = new HashMap<>();
        for (int[] road : roads)    {
            int u = road[0];
            int v = road[1];
            if (!m.containsKey(u))
                m.put(u, new LinkedList<>());
            if (!m.containsKey(v))
                m.put(v, new LinkedList<>());
            m.get(u).add(v);
            m.get(v).add(u);
        }
        
        dfs(0, -1);
        
        return ans;
    }
    
    int dfs(int u, int prev)  {
        int cnt = 0;
        for (int v : m.get(u))  {
            if (v == prev)
                continue;
            cnt += dfs(v, u);
        }
        if (u != 0) {
            cnt++;
            ans += cnt / seats + (cnt % seats == 0 ? 0 : 1);
        }
        return cnt;
    }
}