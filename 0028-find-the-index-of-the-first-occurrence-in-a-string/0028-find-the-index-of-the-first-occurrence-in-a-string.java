class Solution {
    public int strStr(String s, String t) {
        int n = s.length() + t.length() + 1;
        int[] pi = prefix(t + "#" + s);
        for (int i = t.length() + 1; i < n; i++)
            if (pi[i] == t.length())
                return i - 2 * t.length();
        
        return -1;
    }
    
    int[] prefix(String s)  {
        int[] pi = new int[s.length()];
        for (int i = 1; i < s.length(); i++)    {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            pi[i] = j;
        }
        
        return pi;
    }
}