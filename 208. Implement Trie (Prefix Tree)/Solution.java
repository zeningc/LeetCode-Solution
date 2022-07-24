class Trie {
    TrieNode root;
    public Trie() {
        root = new TrieNode('#');
    }
    
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray())   {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode(c);
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray())   {
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return node.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray())   {
            if (node.children[c - 'a'] == null)
                return false;
            node = node.children[c - 'a'];
        }
        return true;
    }
}

class TrieNode  {
    char c;
    boolean isWord;
    TrieNode[] children;
    public TrieNode(char c) {
        this.c = c;
        isWord = false;
        children = new TrieNode[26];
    }
}
