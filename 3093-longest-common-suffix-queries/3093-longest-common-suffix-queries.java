class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie root = new Trie('#');
        for (int i = 0; i < wordsContainer.length; i++)
            Trie.insertWord(root, wordsContainer[i], i);
        
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            Trie node = root;
            String word = wordsQuery[i];
            ans[i] = node.idx;
            for (int j = word.length() - 1; j >= 0; j--)   {
                char c = word.charAt(j);
                if (node.children[c - 'a'] == null)
                    break;
                node = node.children[c - 'a'];
                ans[i] = node.idx;
            }
        }
        
        return ans;
    }
}

class Trie  {
    int minLen;
    int idx;
    char c;
    Trie[] children;
    
    public Trie(char c)   {
        this.c = c;
        children = new Trie[26];
        minLen = Integer.MAX_VALUE;
        idx = Integer.MAX_VALUE;
    }
    
    static void insertWord(Trie root, String word, int idx)  {
        int len = word.length();
        Trie node = root;
        for (int i = word.length() - 1; i >= 0; i--)    {
            if (node.minLen > len || node.minLen == len && idx < node.idx)  {
                node.minLen = len;
                node.idx = idx;
            }
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new Trie(c);
            node = node.children[c - 'a'];
        }
        if (node.minLen > len || node.minLen == len && idx < node.idx)  {
            node.minLen = len;
            node.idx = idx;
        }
    }
}