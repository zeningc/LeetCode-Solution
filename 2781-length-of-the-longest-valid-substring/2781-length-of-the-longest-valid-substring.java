class Solution {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Trie root = new Trie('#', false);
        for (String s : forbidden)
            Trie.insert(root, s);
        int n = word.length();
        int last = n;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--)    {
            Trie node = root;
            for (int j = i; j < Math.min(n, i + 10); j++)    {
                if (node.children[word.charAt(j) - 'a'] == null)
                    break;
                node = node.children[word.charAt(j) - 'a'];
                if (node.isBan)
                    last = Math.min(last, j);
            }
            ans = Math.max(ans, last - i);
        }
        
        return ans;
    }
    
    
}

class Trie  {
    char c;
    boolean isBan;
    Trie[] children;
    String word;
    
    public Trie(char c, boolean isBan)  {
        this.c = c;
        this.isBan = isBan;
        this.children = new Trie[26];
    }
    
    static void insert(Trie root, String s)   {
        Trie node = root;
        for (char c : s.toCharArray())  {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new Trie(c, false);
            node = node.children[c - 'a'];
        }
        node.isBan = true;
        node.word = s;
    }
}