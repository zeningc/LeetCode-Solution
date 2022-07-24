class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] freq = new int[26];
            for (char c : str.toCharArray())    {
                freq[c - 'a']++;
            }
           
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++)    {
                sb.append("#");
                sb.append(String.valueOf(freq[i]));
            }
            String s = sb.toString();
            if (!map.containsKey(s))
                map.put(s, new LinkedList<>());
            
            map.get(s).add(str);
        }
        
        List<List<String>> ans = new LinkedList<>();
        
        for (String key : map.keySet())  {
            ans.add(map.get(key));
        }
        
        return ans;
    }
}
