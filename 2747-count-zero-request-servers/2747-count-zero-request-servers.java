class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int[] resp = new int[queries.length];
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);
        Deque<int[]> q = new LinkedList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] log : logs)
            ts.add(log[1]);
        for (int query : queries)
            ts.add(query);
        
        Map<Integer, List<Integer>> queryMap = new HashMap<>();
        for (int i = 0; i < queries.length; i++)   {
            int query = queries[i];
            if (!queryMap.containsKey(query))
                queryMap.put(query, new LinkedList<>());
            queryMap.get(query).add(i);
        }
        
        int j = 0;
        for (int i : ts)  {
            while (j < logs.length && i == logs[j][1])    {
                int id = logs[j][0];
                int time = logs[j][1];
                freq.put(id, freq.getOrDefault(id, 0) + 1);
                q.offer(logs[j]);
                j++;
            }
            while (!q.isEmpty() && q.peek()[1] < i - x)  {
                int[] pop = q.poll();
                int popId = pop[0];
                freq.put(popId, freq.get(popId) - 1);
                if (freq.get(popId) == 0)
                    freq.remove(popId);
            }
            if (queryMap.containsKey(i))   {
                for (int idx : queryMap.get(i)) {
                    resp[idx] = n - freq.size();
                }
            }
        }
        
        return resp;
    }
}

