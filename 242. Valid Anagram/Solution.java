class Solution {
    public boolean isAnagram(String s, String t) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for (char c : s.toCharArray())
            freq1[c- 'a']++;
        for (char c : t.toCharArray())
            freq2[c- 'a']++;
        for (int i = 0; i < 26; i++)
            if (freq1[i] != freq2[i])
                return false;
        return true;
    }
}
