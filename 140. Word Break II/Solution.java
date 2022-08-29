class Solution {
    List<String> ans;
    Trie root;
    String s;
    public List<String> wordBreak(String s, List<String> wordDict) {
        ans = new LinkedList<>();
        root = new Trie();
        this.s = s;
        for (String word : wordDict)
            trieInsert(root, word);
        
        dfs(new LinkedList<>(), root, 0);
        
        return ans;
    }
    
    void dfs(List<String> path, Trie node, int idx) {
        if (idx >= s.length())  {
            if (node != root)
                return;
            StringBuilder sb = new StringBuilder();
            for (String word : path)    {
                sb.append(word);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            ans.add(sb.toString());
            return;
        }
        
        char c = s.charAt(idx);
        if (node.children[c - 'a'] == null)
            return;
        node = node.children[c - 'a'];
        if (node.isWord)    {
            path.add(node.word);
            dfs(path, root, idx + 1);
            path.remove(path.size() - 1);
        }
        dfs(path, node, idx + 1);
    }
    
    void trieInsert(Trie node, String word) {
        for (char c : word.toCharArray())   {
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new Trie();
            node = node.children[c - 'a'];
        }
        node.isWord = true;
        node.word = word;
    }
    
    
}

class Trie  {
    Trie[] children;
    String word;
    boolean isWord;
    
    public Trie() {
        children = new Trie[26];
        isWord = false;
        word = null;
    }
}
