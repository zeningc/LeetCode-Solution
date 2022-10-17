class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String r : recipes)
            set.add(r);
        
        int n = recipes.length;
        for (int i = 0; i < n; i++) {
            String v = recipes[i];
            if (ingredients.get(i).isEmpty())
                continue;
            for (String u : ingredients.get(i)) {
                if (!graph.containsKey(u))
                    graph.put(u, new LinkedList<>());
                graph.get(u).add(v);
            }
            
            indeg.put(v, ingredients.get(i).size());
        }
        
        Deque<String> q = new LinkedList<>();
        
        for (String s : supplies)   {
            q.offer(s);
        }
        
        List<String> ans = new LinkedList<>();
        while (!q.isEmpty())    {
            String u = q.poll();
            if (set.contains(u))
                ans.add(u);
            if (!graph.containsKey(u))
                continue;
            for (String v : graph.get(u))  {
                indeg.put(v, indeg.getOrDefault(v, 0) - 1);
                if (indeg.get(v) == 0)
                    q.offer(v);
            }
        }
        
        
        return ans;
    }
}