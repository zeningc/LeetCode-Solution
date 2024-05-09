class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Map<Pair<Integer, Integer>, List<int[]>> map = new HashMap<>();
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (int[] r : specialRoads)    {
            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];
            int cost = r[4];
            Pair<Integer, Integer> pair1 = new Pair<Integer, Integer>(x1, y1);
            Pair<Integer, Integer> pair2 = new Pair<Integer, Integer>(x2, y2);
            set.add(pair1);
            set.add(pair2);
            if (dist(pair1, pair2) < cost)
                continue;
            map.computeIfAbsent(pair1, x -> new ArrayList<>()).add(new int[] {x2, y2, cost});
        }
        
        set.add(new Pair<Integer, Integer>(start[0], start[1]));
        set.add(new Pair<Integer, Integer>(target[0], target[1]));
        Set<Pair<Integer, Integer>> vis = new HashSet<>();
        Pair<Integer, Integer> targetPair = new Pair<Integer, Integer>(target[0], target[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {start[0], start[1], 0});
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int c = cur[2];
            Pair<Integer, Integer> curPair = new Pair<Integer, Integer>(x, y);
            if (vis.contains(curPair))
                continue;
            vis.add(curPair);
            if (dist(curPair, targetPair) == 0)
                return c;
            for (int[] nxt : map.getOrDefault(curPair, new ArrayList<>()))    {
                Pair<Integer, Integer> nxtPair = new Pair<>(nxt[0], nxt[1]);
                int nxtCost = nxt[2];
                if (vis.contains(nxtPair))
                    continue;
                pq.offer(new int[] {nxt[0], nxt[1], c + nxtCost});
            }
            
            for (Pair<Integer, Integer> nxtPair : set)  {
                if (vis.contains(nxtPair))
                    continue;
                int nxtCost = dist(curPair, nxtPair);
                pq.offer(new int[] {nxtPair.getKey(), nxtPair.getValue(), c + nxtCost});
            }
        }
        
        return -1;
    }
    
    int dist(int x1, int y1, int x2, int y2)    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    int dist(Pair<Integer, Integer> a, Pair<Integer, Integer> b)    {
        return Math.abs(a.getKey() - b.getKey()) + Math.abs(a.getValue() - b.getValue());
    }
}