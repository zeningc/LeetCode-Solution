class Solution {
    public int[] sumPrefixScores(String[] words) {
        int n = words.length;
        int[] ans = new int[n];
        TrieNode root = new TrieNode();
        for (String word : words)
            root.insert(word);
        for (int i = 0; i < n; i++)
            ans[i] = root.sumScore(words[i]);
        return ans;
    }
}

class TrieNode  {
    int cnt;
    TrieNode[] children;
    
    public TrieNode()   {
        cnt = 0;
        children = new TrieNode[26];
    }
    
    public void insert(String word)    {
        TrieNode node = this;
        for (char c : word.toCharArray())   {
            node.cnt++;
            if (node.children[c - 'a'] == null)
                node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
        }
        node.cnt++;
    }
    
    public int sumScore(String word)   {
        TrieNode node = this;
        int sum = 0;
        for (char c : word.toCharArray())   {
            if (node.children[c - 'a'] == null)
                return -1;
            node = node.children[c - 'a'];
            sum += node.cnt;
        }
        return sum;
    }
}