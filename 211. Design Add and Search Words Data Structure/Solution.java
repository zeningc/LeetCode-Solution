class WordDictionary {
    Trie root;
    public WordDictionary() {
        root = new Trie();
    }
    
    public void addWord(String word) {
        Trie node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new Trie();
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return dfs(word, root);
    }
    
    boolean dfs(String word, Trie node) {
        int n = word.length();
        for (int i = 0; i < n; i++)   {
            char c = word.charAt(i);
            if (c == '.')   {
                String substr = word.substring(i + 1, n);
                for (int j = 0; j < 26; j++)    {
                    if (node.children[j] == null)
                        continue;
                    if (dfs(substr, node.children[j]))
                        return true;
                }
                return false;
            }
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }
}

class Trie  {
    Trie[] children = new Trie[26];
    boolean isWord;
    
    public Trie()   {
        children = new Trie[26];
        isWord = false;
    }
}
