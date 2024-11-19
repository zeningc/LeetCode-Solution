class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Map<Pair<Integer, Integer>, List<int[]>> map = new HashMap<>();
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (int[] r : specialRoads)    {
            Pair<Integer, Integer> p1 = new Pair<Integer, Integer>(r[0], r[1]);
            Pair<Integer, Integer> p2 = new Pair<Integer, Integer>(r[2], r[3]);
            set.add(p1);
            set.add(p2);
            map.computeIfAbsent(p1, x-> new ArrayList<>()).add(new int[] {r[2], r[3], r[4]});
        }
            
        List<Pair<Integer, Integer>> list = new ArrayList<>(set);
        list.add(new Pair<Integer, Integer>(start[0], start[1]));
        list.add(new Pair<Integer, Integer>(target[0], target[1]));
        for (int i = 0; i < list.size(); i++)   {
            for (int j = i + 1; j < list.size(); j++)   {
                int x1 = list.get(i).getKey();
                int y1 = list.get(i).getValue();
                int x2 = list.get(j).getKey();
                int y2 = list.get(j).getValue();
                int dist = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                map.computeIfAbsent(list.get(i), x-> new ArrayList<>()).add(new int[] {x2, y2, dist});
                map.computeIfAbsent(list.get(j), x-> new ArrayList<>()).add(new int[] {x1, y1, dist});
            }
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        Set<Pair<Integer, Integer>> vis = new HashSet<>();
        pq.offer(new int[] {start[0], start[1], 0});
        
        while (!pq.isEmpty())   {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int cost = cur[2];
            Pair<Integer, Integer> curPair = new Pair<Integer, Integer>(x, y);
            if (vis.contains(curPair))
                continue;
            vis.add(curPair);
            if (x == target[0] && y == target[1])
                return cost;
            
            for (int[] nxt : map.getOrDefault(curPair, new ArrayList<>()))  {
                int nx = nxt[0];
                int ny = nxt[1];
                Pair<Integer, Integer> nxtPair = new Pair<Integer, Integer>(nx, ny);
                if (vis.contains(nxtPair))
                    continue;
                int add = nxt[2];
                pq.offer(new int[] {nx, ny, cost + add});
            }
        }
        
        return -1;
    }
}