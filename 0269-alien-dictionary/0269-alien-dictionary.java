class Solution {
    public String alienOrder(String[] words) {
        Set<Integer> set = new HashSet<>();
        for (String word : words)   {
            for (char c : word.toCharArray())   {
                set.add(c - 'a');
            }
        }
        
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Set<Integer>> in = new HashMap<>();
        for (int i = 1; i < words.length; i++)  {
            boolean flag = false;
            for (int k = 0; k < Math.min(words[i].length(), words[i - 1].length()); k++) {
                int a = words[i - 1].charAt(k) - 'a';
                int b = words[i].charAt(k) - 'a';
                if (a == b)
                    continue;
                    
                in.computeIfAbsent(b, x -> new HashSet<>()).add(a);
                graph.computeIfAbsent(a, x -> new HashSet<>()).add(b);
                flag = true;
                break;
            }
            if (!flag && words[i - 1].length() > words[i].length())
                return "";
        }
        
        int[] inDegree = new int[26];
        for (int i : set)
            inDegree[i] = in.getOrDefault(i, new HashSet<>()).size();
        Deque<Integer> q = new LinkedList<>();
        for (int i : set)
            if (inDegree[i] == 0)
                q.offer(i);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty())    {
            int u = q.poll();
            
            sb.append((char)(u + 'a'));
            for (int v : graph.getOrDefault(u, new HashSet<>()))    {
                inDegree[v]--;
                if (inDegree[v] == 0)
                    q.offer(v);
            }
        }
        
        return sb.length() == set.size() ? sb.toString() : "";
    }
}