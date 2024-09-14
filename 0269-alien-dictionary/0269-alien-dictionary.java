class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> edges;
        Map<Character, Integer> inDegree;
        int n = words.length;
        String prev = words[0];
        edges = new HashMap<>();
        inDegree = new HashMap<>();
        for (String word : words)   {
            for (char c : word.toCharArray())   {
                if (!inDegree.containsKey(c))   {
                    inDegree.put(c, 0);
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (prev.length() > words[i].length() && prev.substring(0, words[i].length()).equals(words[i])) {
                return "";
            }
            for (int j = 0; j < Math.min(prev.length(), words[i].length()); j++) {
                if (prev.charAt(j) == words[i].charAt(j))   {
                    continue;
                }
                char u = prev.charAt(j);
                char v = words[i].charAt(j);
                if (!edges.containsKey(u))   {
                    edges.put(u, new HashSet<>());
                }
                if (edges.get(u).contains(v))   {
                    break;
                }
                edges.get(u).add(v);
                inDegree.put(v, inDegree.get(v) + 1);
                break;
            }
            prev = words[i];
        }
        
        StringBuilder ans = new StringBuilder();
        Deque<Character> queue = new LinkedList<>();
        for (char c : inDegree.keySet())    {
            if (inDegree.get(c) == 0)   {
                queue.offer(c);
            }
        }
        
        while (!queue.isEmpty())    {
            char c = queue.poll();
            ans.append(c);
            if (edges.containsKey(c))   {
                for (char v : edges.get(c)) {
                    inDegree.put(v, inDegree.get(v) - 1);
                    if (inDegree.get(v) == 0)   {
                        queue.offer(v);
                    }
                }
            }
        }
        
        if (ans.length() == inDegree.size())    {
            return ans.toString();
        }
        return "";
    }
    
    
}

