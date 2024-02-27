class Solution {
    public int minimumTimeToInitialState(String word, int k) {
        int[] pi = prefixFunc(word);
        int a = word.length() / k + (word.length() % k == 0 ? 0 : 1);
        int j = pi[word.length() - 1];
        while (j > 0 && (word.length() - j) % k != 0)
            j = pi[j - 1];
        if ((word.length() - j) % k != 0)
            return a;
        return Math.min(a, (word.length() - j) / k);
    }
    
    int[] prefixFunc(String s)  {
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
        return pi;
    }
}