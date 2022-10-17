class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        Map<Integer, int[]> colorRect = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indeg = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j];
                if (!colorRect.containsKey(color))  {
                    colorRect.put(color, new int[] {i, j, i, j});
                    continue;
                }
                int[] t = colorRect.get(color);
                t[0] = Math.min(t[0], i);
                t[1] = Math.min(t[1], j);
                t[2] = Math.max(t[2], i);
                t[3] = Math.max(t[3], j);
            }
        }
        
        for (int key : colorRect.keySet())  {
            graph.put(key, new HashSet<>());
            indeg.put(key, 0);
        }
        
        for (int key : colorRect.keySet())  {
            int[] range = colorRect.get(key);
            for (int i = range[0]; i <= range[2]; i++)  {
                for (int j = range[1]; j <= range[3]; j++)  {
                    int finalColor = targetGrid[i][j];
                    if (finalColor != key)  {
                        if (!graph.get(key).contains(finalColor))   {
                            indeg.put(finalColor, indeg.get(finalColor) + 1);
                            graph.get(key).add(finalColor);
                        }
                    }
                }
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        for (int key : colorRect.keySet())
            if (indeg.get(key) == 0)
                q.offer(key);
        int cnt = 0;
        while (!q.isEmpty())    {
            int u = q.poll();
            cnt++;
            if (graph.get(u).isEmpty())
                continue;
            for (int v : graph.get(u))  {
                indeg.put(v, indeg.get(v) - 1);
                if (indeg.get(v) == 0)
                    q.offer(v);
            }
        }
        
        return cnt == colorRect.size();
    }
}