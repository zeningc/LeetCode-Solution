class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> edges = new HashMap<>();
        Map<Character, Set<Character>> in = new HashMap<>();
        int[] inDegree = new int[26];
        int n = words.length;
        Set<Character> set = new HashSet<>();
        
        for (String word : words)   {
            for (char c : word.toCharArray())   {
                set.add(c);
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                boolean flag = false;
                for (int k = 0; k < Math.min(words[i].length(), words[j].length()); k++)    {
                    if (words[i].charAt(k) == words[j].charAt(k))
                        continue;
                    if (!edges.containsKey(words[i].charAt(k)))
                        edges.put(words[i].charAt(k), new HashSet<>());
                    if (!in.containsKey(words[j].charAt(k)))
                        in.put(words[j].charAt(k), new HashSet<>());
                    flag = true;
                    edges.get(words[i].charAt(k)).add(words[j].charAt(k));
                    in.get(words[j].charAt(k)).add(words[i].charAt(k));
                    break;
                }
                if (!flag && words[i].length() > words[j].length())
                    return "";
            }
        }
        
        for (char c : set)   {
            int len = in.containsKey(c) ? in.get(c).size() : 0;
            inDegree[c - 'a'] = len;
        }
        
        Deque<Character> q = new LinkedList<>();
        
        for (char c : set)
            if (inDegree[c - 'a'] == 0)
                q.offer(c);
        
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            if (!edges.containsKey(c))
                continue;
            for (char v : edges.get(c)) {
                inDegree[v - 'a']--;
                if (inDegree[v - 'a'] == 0)
                    q.offer(v);
            }
        }
        
        return sb.length() == set.size() ? sb.toString() : "";
    }
}
