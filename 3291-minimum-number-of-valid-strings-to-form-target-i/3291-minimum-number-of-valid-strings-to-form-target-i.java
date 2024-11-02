class Solution {
    public int minValidStrings(String[] words, String target) {
        Trie root = new Trie('#');
        
        for (String word : words)   {
            Trie node = root;
            for (char c : word.toCharArray())   {
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new Trie(c);
                node = node.children[c - 'a'];
            }
        }
        
        int[] dp = new int[target.length() + 1];
        for (int i = target.length() - 1; i >= 0; i--)  {
            dp[i] = Integer.MAX_VALUE / 2;
            Trie node = root;
            for (int j = i; j < target.length(); j++)   {
                if (node.children[target.charAt(j) - 'a'] == null)
                    break;
                dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                node = node.children[target.charAt(j) - 'a'];
            }
        }
        
        
        return dp[0] >= Integer.MAX_VALUE / 2 ? -1: dp[0];
    }
}

class Trie  {
    char c;
    Trie[] children;
    
    public Trie(char c) {
        children = new Trie[26];
        this.c = c;
    }
}