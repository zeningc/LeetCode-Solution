class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++)   {
            String word = words[i];
            map.computeIfAbsent(word.charAt(0), x -> new ArrayList<>()).add(new int[] {i, 0, word.length()});
        }
        int ans = 0;
        for (char c : s.toCharArray())  {
            if (!map.containsKey(c))
                continue;
            List<int[]> list = map.get(c);
            map.remove(c);
            for (int[] cur : list)  {
                cur[1]++;
                if (cur[1] < cur[2])    {
                    map.computeIfAbsent(words[cur[0]].charAt(cur[1]), x -> new ArrayList<>()).add(cur);
                    continue;
                }
                ans++;
            }
        }
        
        return ans;
    }
}
