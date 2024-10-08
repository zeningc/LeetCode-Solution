class Solution {
    public int strStr(String haystack, String needle) {
        int[] prefix = prefixFunction(needle + "#" + haystack);
        for (int i = needle.length() + 1; i < haystack.length() + needle.length() + 1; i++)
            if (prefix[i] == needle.length())
                return i - needle.length() + 1 - needle.length() - 1;
        return -1;
    }
    
    int[] prefixFunction(String s)  {
        int[] pi = new int[s.length()];
        for (int i = 1; i < s.length(); i++)    {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = pi[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            pi[i] = j;
        }
        
        return pi;
    }
}