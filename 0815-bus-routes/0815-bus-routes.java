class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        Map<Integer, Set<Integer>> nodeToLine = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++)  {
                nodeToLine.computeIfAbsent(routes[i][j], x -> new HashSet<>()).add(i);
            }
        }
        
        
        

        Deque<Integer> q = new LinkedList<>();
        q.offer(source);
        int busCnt = 0;
        Set<Integer> visRoute = new HashSet<>();
        Set<Integer> visStop = new HashSet<>();
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (visStop.contains(u))
                    continue;
                visStop.add(u);
                if (target == u)
                    return busCnt;
                for (int r : nodeToLine.getOrDefault(u, new HashSet<>()))    {
                    if (visRoute.contains(r))
                        continue;
                    visRoute.add(r);
                    for (int v : routes[r]) {
                        if (visStop.contains(v))
                            continue;
                        q.offer(v);
                    }
                }
            }
            busCnt++;
        }
        return -1;
    }
    
    
}