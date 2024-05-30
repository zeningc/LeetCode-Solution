class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int len = 0;
        int lo = 0;
        for (int hi = 0; hi < s.length(); hi++)  {
            char c = s.charAt(hi);
            while (set.contains(c)) {
                set.remove(s.charAt(lo));
                lo++;
            }
            set.add(c);
            len = Math.max(len, hi - lo + 1);
        }
        
        return len;
    }
}