class Solution {
    public int magnificentSets(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges)    {
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }
        Map<Integer, Integer> unionCnt = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int[] visited = new int[n + 1];
            Arrays.fill(visited, -1);
            Deque<Integer> queue = new LinkedList<>();
            queue.add(i);
            int label = 0;
            int grpId = Integer.MAX_VALUE;
            while (!queue.isEmpty())    {
                int size = queue.size();
                while (size-- > 0)  {
                    int u = queue.poll();
                    grpId = Math.min(grpId, u);
                    if (visited[u] != -1)
                        continue;
                    visited[u] = label;
                    
                    for (int v : graph.getOrDefault(u, new ArrayList<>()))  {
                        if (visited[v] != -1)   {
                            if (Math.abs(visited[v] - label) != 1)  {
                                return -1;
                            }
                            continue;
                        }
                        queue.offer(v);
                    }
                }
                label++;
            }
            
            unionCnt.put(grpId, Math.max(unionCnt.getOrDefault(grpId, 0), label));
        }
        int ans = 0;
        for (int value : unionCnt.values())
            ans += value;
        return ans;
    }
}