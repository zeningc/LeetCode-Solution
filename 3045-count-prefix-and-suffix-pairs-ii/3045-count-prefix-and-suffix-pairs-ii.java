class Solution {
    public long countPrefixSuffixPairs(String[] words) {
        long ans = 0;
        Trie root = new Trie('#');
        for (int idx = words.length - 1; idx >= 0; idx--)   {
            String word = words[idx];
            ans += Trie.search(root, word);
            int[] pi = new int[word.length()];
            for (int i = 1; i < word.length(); i++) {
                int j = pi[i - 1];
                while (j > 0 && word.charAt(j) != word.charAt(i))
                    j = pi[j - 1];
                if (word.charAt(j) == word.charAt(i))
                    j++;
                pi[i] = j;
            }
            Set<Integer> set = new HashSet<>();
            set.add(word.length());
            int j = pi[word.length() - 1];
            while (j > 0)   {
                set.add(j);
                j = pi[j - 1];
            }
            Trie.insert(root, set, word);
        }
        
        
        
        return ans;
    }
}

class Trie  {
    char c;
    Trie[] children;
    long cnt;
    
    public Trie(char c) {
        this.c = c;
        children = new Trie[26];
        long cnt = 0;
    }
    
    static void insert(Trie root, Set<Integer> set, String s) {
        for (int i = 0; i < s.length(); i++)  {
            char c = s.charAt(i);
            if (root.children[c - 'a'] == null)
                root.children[c - 'a'] = new Trie(c);
            root = root.children[c - 'a'];
            if (set.contains(i + 1))
                root.cnt++;
        }
    }
    
    static long search(Trie root, String s)  {
        for (char c : s.toCharArray())  {
            if (root.children[c - 'a'] == null)
                return 0;
            root = root.children[c - 'a'];
        }
        return root.cnt;
    }
}