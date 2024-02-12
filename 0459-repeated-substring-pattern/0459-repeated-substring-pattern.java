class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j))
                j++;
            pi[i] = j;
        }
        int i = n - 1;
        while (pi[i] > 0 && (pi[i] * 2 > s.length() || s.length() % pi[i] != 0))  {
            i = pi[i - 1];
        }
        if (pi[i] == 0)
            return false;
        if (s.length() % pi[i] != 0)
            return false;
        for (int j = n - 1; j >= pi[i]; j -= pi[i])
            if (pi[j] < pi[i])
                return false;
        return true;
    }
}