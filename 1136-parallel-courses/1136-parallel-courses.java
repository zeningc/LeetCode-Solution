class Solution {
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        int[] indeg = new int[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            edges.put(i, new LinkedList<>());
        }
        
        for (int[] relation : relations)    {
            int u = relation[0];
            int v = relation[1];
            edges.get(u).add(v);
            indeg[v]++;
        }
        
        Deque<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            if (indeg[i] == 0)  {
                queue.offer(i);
                count++;
            }
        }
        int ans = 0;
        while (!queue.isEmpty())    {
            int size = queue.size();
            for (int i = 0; i < size; i++)  {
                int u = queue.poll();
                for (int v : edges.get(u))  {
                    indeg[v]--;
                    if (indeg[v] == 0)  {
                        count++;
                        queue.offer(v);
                    }
                }
            }
            ans++;
        }
        
        return count == n ? ans : -1;
    }
}