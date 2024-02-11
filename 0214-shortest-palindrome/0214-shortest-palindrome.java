class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() == 0)
            return "";
        int[] pre = prefixFunc(s);
        String r = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[] pi = new int[n];
        pi[0] = s.charAt(0) == r.charAt(0) ? 1 : 0;
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(j) != r.charAt(i))
                j = pre[j - 1];
            if (s.charAt(j) == r.charAt(i))
                j++;
            pi[i] = j;
        }
        int len = pi[n - 1];
        return new StringBuilder(s.substring(len)).reverse().toString() + s;
    }
    
    int[] prefixFunc(String s)  {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
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