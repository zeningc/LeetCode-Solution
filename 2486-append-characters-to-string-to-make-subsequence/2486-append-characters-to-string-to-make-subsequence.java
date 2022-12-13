class Solution {
    public int appendCharacters(String s, String t) {
        int i = 0;
        int j = 0;
        int n = s.length();
        int m = t.length();
        while (i < n && j < m)   {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            }
            i++;
        }
        return m - j;
    }
}