class Solution {
    Map<Integer, List<Integer>> m;
    int[] cost;
    long[] ans;
    int[] size;
    public long[] placedCoins(int[][] edges, int[] cost) {
        m = new HashMap<>();
        this.cost = cost;
        ans = new long[cost.length];
        size = new int[cost.length];
        for (int[] e : edges)   {
            m.computeIfAbsent(e[0], x -> new ArrayList<>()).add(e[1]);
            m.computeIfAbsent(e[1], x -> new ArrayList<>()).add(e[0]);
        }
        calcSize(0, -1);
        dfs(0, -1);
        
        return ans;
    }
    
    int calcSize(int u, int p)  {
        List<Integer> children = m.getOrDefault(u, new ArrayList<>()).stream().filter(c -> !c.equals(p)).toList();
        int cnt = 1;
        for (int v : children)  {
            cnt += calcSize(v, u);        
        }
        size[u] = cnt;
        return cnt;
    }
    
    long[] dfs(int u, int p)   {
        List<Integer> children = m.getOrDefault(u, new ArrayList<>()).stream().filter(c -> !c.equals(p)).toList();
        PriorityQueue<Long> pq1 = new PriorityQueue<>();
        PriorityQueue<Long> pq2 = new PriorityQueue<>((a, b) -> b.compareTo(a));
        pq1.offer((long)cost[u]);
        pq2.offer((long)cost[u]);
        for (int v : children)  {
            long[] subTreeMaxCost = dfs(v, u);
            for (int i = 0; i < 3; i++) {
                if (pq1.size() < 3 || pq1.peek() < subTreeMaxCost[i])
                    pq1.offer(subTreeMaxCost[i]);
                if (pq1.size() > 3)
                    pq1.poll();
            }
            for (int i = 3; i < 6; i++) {
                if (pq2.size() < 3 || pq2.peek() > subTreeMaxCost[i])
                    pq2.offer(subTreeMaxCost[i]);
                if (pq2.size() > 3)
                    pq2.poll();
            }
        }
        long[] maxCost = new long[6];
        int idx = 0;
        while (idx < 3 - pq1.size())
            maxCost[idx++] = Integer.MIN_VALUE;
        while (!pq1.isEmpty())
            maxCost[idx++] = pq1.poll();
        while (idx < 6 - pq2.size())
            maxCost[idx++] = Integer.MAX_VALUE;
        while (!pq2.isEmpty())
            maxCost[idx++] = pq2.poll();
        ans[u] = size[u] < 3 ? 1 : calc(maxCost);
        return maxCost;
    }
    
    long calc(long[] maxCost)   {
        long x = maxCost[2] * maxCost[1] * maxCost[0];
        long y = maxCost[5] * maxCost[4] * maxCost[2];
        return Math.max(0, (Math.max(x, y)));
    }
}