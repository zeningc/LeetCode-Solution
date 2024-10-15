class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        Map<Integer, List<Integer>> stopToRoute = new HashMap<>();
        
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for (int r : route) {
                stopToRoute.computeIfAbsent(r, x -> new ArrayList<>()).add(i);
            }
        }
        
        Deque<Integer> q = new LinkedList<>();
        Set<Integer> visStop = new HashSet<>();
        Set<Integer> visRoute = new HashSet<>();
        q.offer(source);
        int dist = 0;
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (visStop.contains(u))
                    continue;
                visStop.add(u);
                
                if (u == target)
                    return dist;
                
                for (int route : stopToRoute.getOrDefault(u, new ArrayList<>()))   {
                    if (visRoute.contains(route))
                        continue;
                    visRoute.add(route);
                    for (int v : routes[route]) {
                        if (visStop.contains(v))
                            continue;
                        q.offer(v);
                    }
                }
            }
            dist++;
        }
        
        return -1;
    }
}