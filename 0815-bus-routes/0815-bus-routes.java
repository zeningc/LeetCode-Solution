class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target)
            return 0;
        Map<Integer, Set<Integer>> nodeToLine = new HashMap<>();
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++)  {
                if (routes[i][j] == source)
                    sources.add(i);
                if (routes[i][j] == target)
                    targets.add(i);
                nodeToLine.computeIfAbsent(routes[i][j], x -> new HashSet<>()).add(i);
            }
        }
        
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (Set<Integer> value : nodeToLine.values())   {
            List<Integer> valueList = new ArrayList<>(value);
            for (int i = 0; i < valueList.size(); i++)  {
                for (int j = i + 1; j < valueList.size(); j++)  {
                    graph.computeIfAbsent(valueList.get(i), x -> new HashSet<>()).add(valueList.get(j));
                    graph.computeIfAbsent(valueList.get(j), x -> new HashSet<>()).add(valueList.get(i));
                }
            }
        }

        Deque<Integer> q = new LinkedList<>();
        for (int s : sources)
            q.offer(s);
        int busCnt = 1;
        Set<Integer> vis = new HashSet<>();
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int u = q.poll();
                if (vis.contains(u))
                    continue;
                vis.add(u);
                if (targets.contains(u))
                    return busCnt;
                for (int v : graph.getOrDefault(u, new HashSet<>()))    {
                    if (vis.contains(v))
                        continue;
                    q.offer(v);
                }
            }
            busCnt++;
        }
        return -1;
    }
    
    
}