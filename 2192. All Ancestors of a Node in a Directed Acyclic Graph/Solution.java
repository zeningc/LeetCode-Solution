class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[n];
        for (int[] edge : edges) {  
            int u = edge[0];
            int v = edge[1];
            if (!graph.containsKey(u)) {
                graph.put(u, new LinkedList<>());
            }
            graph.get(u).add(v);
            inDegree[v]++;  
        }  
        List<TreeSet<Integer>> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            ans.add(new TreeSet<>());
        }

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0)
                q.add(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            if (graph.containsKey(u)) {
                for (int v : graph.get(u)) {
                    ans.get(v).add(u);
                    ans.get(v).addAll(ans.get(u));
                    inDegree[v]--;
                    if (inDegree[v] == 0)
                        q.add(v);
                }
            }
        }
        List<List<Integer>> ansList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<Integer> t = new LinkedList<>(ans.get(i));
            ansList.add(t);
        }

        return ansList;
    }
}