class Solution {
    public long countPalindromePaths(List<Integer> parent, String s) {
        Map<Integer, List<Integer>> graphMap = new HashMap<>();
        Map<Integer, List<Character>> graphCharMap = new HashMap<>();
        Map<Integer, Integer> bitMaskCounterMap = new HashMap<>();
        for (int i = 1; i < parent.size(); i++) {
            int v = i;
            int u = parent.get(v);
            if (!graphMap.containsKey(u))
                graphMap.put(u, new ArrayList<>());
            if (!graphCharMap.containsKey(u))
                graphCharMap.put(u, new ArrayList<>());
            
            graphMap.get(u).add(v);
            graphCharMap.get(u).add(s.charAt(v));
        }
        
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        
        long cnt = 0;
        
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int cur = poll[0];
            int mask = poll[1];
            if (!bitMaskCounterMap.containsKey(mask))
                bitMaskCounterMap.put(mask, 0);
            cnt += bitMaskCounterMap.get(mask);
            for (int i = 0; i < 26; i++)    {
                // System.out.println((mask ^ (1 << i)));
                cnt += bitMaskCounterMap.getOrDefault((mask ^ (1 << i)), 0);
            }
            bitMaskCounterMap.put(mask, bitMaskCounterMap.get(mask) + 1);
            
            if (!graphMap.containsKey(cur))
                continue;
            
            for (int i = 0; i < graphMap.get(cur).size(); i++)  {
                int next = graphMap.get(cur).get(i);
                char c = graphCharMap.get(cur).get(i);
                q.offer(new int[] {next, mask ^ (1 << (c - 'a'))});
            }
        }
        
        return cnt;        
    }
}