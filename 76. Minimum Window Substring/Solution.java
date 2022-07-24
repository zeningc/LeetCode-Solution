class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        Map<Character, Integer> freq1 = new HashMap<>();
        Map<Character, Integer> freq2 = new HashMap<>();
        
        String ans = null;
        
        for (char c : t.toCharArray())
            freq1.put(c, freq1.getOrDefault(c, 0) + 1);
        
        int left = 0;
        int right = 0;
        for (right = 0; right < s.length(); right++)    {
            char c = s.charAt(right);
            freq2.put(c, freq2.getOrDefault(c, 0) + 1);
            while (check(freq1, freq2)) {
                if (ans == null || right - left + 1 < ans.length())
                    ans = s.substring(left, right + 1);
                freq2.put(s.charAt(left), freq2.get(s.charAt(left)) - 1);
                left++;
            }
        }
        
        
        return ans == null ? "" : ans;
    }
    
    boolean check(Map<Character, Integer> freq1, Map<Character, Integer> freq2) {
        for (Character c : freq1.keySet())  {
            if (freq1.get(c) > freq2.getOrDefault(c, 0))
                return false;
        }
        return true;
    }
}
