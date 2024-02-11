class Solution {
    public int strStr(String haystack, String needle) {
        int n = haystack.length() + needle.length() + 1;
        String s = needle + "#" + haystack;
        int[] pi = new int[n];
        
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            pi[i] = j;
        }
        
        for (int i = needle.length() + 1; i < n; i++) {
            if (pi[i] == needle.length())
                return i - needle.length() * 2;
        }
        
        return -1;
    }
}