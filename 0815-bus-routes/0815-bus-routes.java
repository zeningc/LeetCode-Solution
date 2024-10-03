class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        
        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();
        for (int i = 0; i < routes.length; i++)  {
            int[] route = routes[i];
            for (int r : route)
                stopToRoutes.computeIfAbsent(r, x -> new ArrayList<>()).add(i);
            
        }
        int dist = 0;
        Set<Integer> vis = new HashSet<>();
        Deque<Integer> q = new LinkedList<>();
        Set<Integer> visRoute = new HashSet<>();
        q.offer(source);
        vis.add(source);
        
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                
                if (u == target)
                    return dist;
                
                for (int idx : stopToRoutes.getOrDefault(u, new ArrayList<>()))   {
                    if (visRoute.contains(idx))
                        continue;
                    for (int v : routes[idx]) {
                        if (vis.contains(v))
                            continue;
                        vis.add(v);
                        q.offer(v);
                    }
                    visRoute.add(idx);
                }
            }
            dist++;
        }
        
        return -1;
    }
}