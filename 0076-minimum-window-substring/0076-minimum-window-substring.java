class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray())
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        int n = s.length();
        int[] next = new int[n];
        Map<Character, Integer> map = new HashMap<>();
        int lo = 0;
        int len = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        int formed = 0;
        int need = tMap.size();
        for (int hi = 0; hi < n; hi++)  {
            char c = s.charAt(hi);
            if (!tMap.containsKey(c))
                continue;
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c).equals(tMap.get(c)))  {
                formed++;
            }
            while (formed == need)    {
                char p = s.charAt(lo);
                if (!tMap.containsKey(p))   {
                    lo++;
                    continue;
                }
                if (map.get(p) > tMap.get(p)) {
                    map.put(p, map.get(p) - 1);
                    lo++;
                    continue;
                }
                break;
            }
            
            if (formed == need && hi - lo + 1 < len)   {
                len = hi - lo + 1;
                start = lo;
                end = hi + 1;
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }
}