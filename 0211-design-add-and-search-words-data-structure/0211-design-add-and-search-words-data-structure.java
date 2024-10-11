class WordDictionary {
    Trie root;
    public WordDictionary() {
        root = new Trie('#');
    }
    
    public void addWord(String word) {
        Trie node = root;
        for (char c : word.toCharArray())   {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new Trie(c);
            node = node.children[c - 'a'];
        }
        node.exists = true;
    }
    
    public boolean search(String word) {
        return search(root, word, 0);
    }
    
    boolean search(Trie node, String word, int idx) {
        if (idx >= word.length())
            return node.exists;
        char c = word.charAt(idx);
        if (c != '.')   {
            if (node.children[c - 'a'] == null)
                return false;
            return search(node.children[c - 'a'], word, idx + 1);
        }
        
        boolean exist = false;
        for (int i = 0; i < 26; i++)    {
            if (node.children[i] == null)
                continue;
            exist |= search(node.children[i], word, idx + 1);
        }
        
        return exist;
    }
    
}

class Trie  {
    Trie[] children;
    boolean exists;
    char c;
    
    public Trie(char c) {
        this.c = c;
        this.children = new Trie[26];
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */