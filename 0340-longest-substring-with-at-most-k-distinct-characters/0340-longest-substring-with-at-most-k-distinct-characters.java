class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        int lo = 0;
        for (int hi = 0; hi < s.length(); hi++) {
            map.put(s.charAt(hi), map.getOrDefault(s.charAt(hi), 0) + 1);
            while (map.size() > k)  {
                map.put(s.charAt(lo), map.get(s.charAt(lo)) - 1);
                if (map.get(s.charAt(lo)) == 0)
                    map.remove(s.charAt(lo));
                lo++;
            }
            len = Math.max(len, hi - lo + 1);
        }
        return len;
    }
}