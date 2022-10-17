class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int[] newGroup = new int[n];
        int cnt = m;
        Map<Integer, List<Integer>> groupItems = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int grp = group[i];
            if (grp == -1)
                grp = cnt++;
            
            if (!groupItems.containsKey(grp))
                groupItems.put(grp, new LinkedList<>());
            groupItems.get(grp).add(i);
            newGroup[i] = grp;
        }
        
        Map<Integer, Set<Integer>> groupGraph = new HashMap<>();
        int[] groupIndeg = new int[cnt];
        
        for (int i = 0; i < n; i++) {
            List<Integer> items = beforeItems.get(i);
            if (items.isEmpty())
                continue;
            int v = newGroup[i];
            for (int itm : items)   {
                int u = newGroup[itm];
                if (u == v || groupGraph.containsKey(u) && groupGraph.get(u).contains(v))
                    continue;
                if (!groupGraph.containsKey(u))
                    groupGraph.put(u, new HashSet<>());
                groupGraph.get(u).add(v);
                groupIndeg[v]++;
                
            }
        }
        
        List<Integer> groupOrder = new LinkedList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < cnt; i++)   
            if (groupIndeg[i] == 0)
                q.offer(i);
        
        
        while (!q.isEmpty())    {
            int u = q.poll();
            groupOrder.add(u);
            if (!groupGraph.containsKey(u))
                continue;
            for (int v : groupGraph.get(u))    {
                groupIndeg[v]--;
                if (groupIndeg[v] == 0)
                    q.offer(v);
            }
        }
        
        if (groupOrder.size() != cnt)
            return new int[0];
        
        Map<Integer, List<Integer>> inOrderMap = new HashMap<>();
        for (int i = 0; i < cnt; i++)
            inOrderMap.put(i, new LinkedList<>());

        for (int i = 0; i < cnt; i++)   {
            if (!groupItems.containsKey(i))
                continue;
            Map<Integer, List<Integer>> inGroupMap = new HashMap<>();
            Map<Integer, Integer> inGroupIndeg = new HashMap<>();
            for (int j : groupItems.get(i)) {
                inGroupIndeg.put(j, 0);
            }
            
            for (int j : groupItems.get(i)) {
                List<Integer> items = beforeItems.get(j);
                if (items.isEmpty())
                    continue;
                for (int itm : items)   {
                    if (newGroup[itm] != i)
                        continue;
                    int u = itm;
                    int v = j;
                    if (!inGroupMap.containsKey(u))
                        inGroupMap.put(u, new LinkedList<>());
                    inGroupMap.get(u).add(v);
                    inGroupIndeg.put(v, inGroupIndeg.get(v) + 1);
                }
            }
            
            for (Map.Entry<Integer, Integer> e : inGroupIndeg.entrySet())   {
                if (e.getValue() == 0)
                    q.offer(e.getKey());
            }
            
            while (!q.isEmpty())    {
                int u = q.poll();
                inOrderMap.get(i).add(u);
                
                if (!inGroupMap.containsKey(u))
                    continue;
                for (int v : inGroupMap.get(u)) {
                    inGroupIndeg.put(v, inGroupIndeg.get(v) - 1);
                    if (inGroupIndeg.get(v) == 0)
                        q.offer(v);
                }
            }
            if (inOrderMap.get(i).size() != groupItems.get(i).size())
                return new int[0];
        }
        
        int[] ans = new int[n];
        int idx = 0;
        for (int grp : groupOrder)  {
            List<Integer> order = inOrderMap.get(grp);
            if (order.isEmpty())
                continue;
            for (int itm : order)   {
                ans[idx++] = itm;
            }
        }
        
        return ans;
    }
}