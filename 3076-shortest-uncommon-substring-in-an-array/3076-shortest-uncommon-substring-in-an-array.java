class Solution {
    public String[] shortestSubstrings(String[] arr) {
        Map<Integer, Set<String>> m = new HashMap<>();
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < arr.length; i++)    {
            String a = arr[i];
            Set<String> set = new HashSet<>();
            for (int j = 0; j < a.length(); j++)    {
                for (int k = j; k < a.length(); k++)    {
                    set.add(a.substring(j, k + 1));
                }
            }
            for (String s : set)
                freq.put(s, freq.getOrDefault(s, 0) + 1);
            m.put(i, set);
        }
        String[] ans = new String[arr.length];
        
        for (int i = 0; i < arr.length; i++)    {
            Set<String> set = m.get(i);
            String cur = null;
            for (String s : set)    {
                if (freq.get(s) != 1)
                    continue;
                if (cur == null)    {
                    cur = s;
                    continue;
                }
                if (s.length() > cur.length())
                    continue;
                if (s.length() == cur.length() && s.compareTo(cur) <= 0 || s.length() < cur.length())
                    cur = s;
            }
            ans[i] = cur == null ? "" : cur;
        }
        
        return ans;
    }
}