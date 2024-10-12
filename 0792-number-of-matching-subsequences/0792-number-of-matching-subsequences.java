class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length();
        int[][] nxt = new int[26][n];
        for (int i = n - 1; i >= 0; i--)    {
            char c = s.charAt(i);
            for (int j = 0; j < 26; j++)
                nxt[j][i] = i != n - 1 ? nxt[j][i + 1] : -1;
            nxt[c - 'a'][i] = i;
        }
        
        int ans = 0;
        for (String word : words)   {
            int p = 0;
            boolean valid = true;
            for (int q = 0; q < word.length(); q++) {
                if (p >= n || nxt[word.charAt(q) - 'a'][p] == -1) {
                    valid = false;
                    break;
                }
                p = nxt[word.charAt(q) - 'a'][p] + 1;
            }
            if (valid)
                ans++;
        }
        
        return ans;
    }
}