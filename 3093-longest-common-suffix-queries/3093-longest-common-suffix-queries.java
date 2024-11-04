class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie root = new Trie('#');
        
        for (int i = 0; i < wordsContainer.length; i++)  {
            String word = wordsContainer[i];
            Trie node = root;
            for (int j = word.length() - 1; j >= 0; j--)   {
                char c = word.charAt(j);
                node.ids.add(i);
                if (node.children[c - 'a'] == null)
                    node.children[c - 'a'] = new Trie(c);
                node = node.children[c - 'a'];
            }
            node.ids.add(i);
        }
        
        int[] ans = new int[wordsQuery.length];
        
        for (int i = 0; i < wordsQuery.length; i++) {
            Trie node = root;
            for (int j = wordsQuery[i].length() - 1; j >= 0; j--)   {
                char c = wordsQuery[i].charAt(j);
                if (node.children[c - 'a'] == null)
                    break;
                node = node.children[c - 'a'];
            }
            
            int ansIdx = -1;
            for (int idx : node.ids)    {
                if (ansIdx == -1 || wordsContainer[ansIdx].length() > wordsContainer[idx].length() || wordsContainer[ansIdx].length() == wordsContainer[idx].length() && idx < ansIdx)  {
                    ansIdx = idx;
                }
            }
            ans[i] = ansIdx;
        }
        
        return ans;
    }
}

class Trie  {
    char c;
    Trie[] children;
    List<Integer> ids;
    
    public Trie(char c) {
        this.c = c;
        children = new Trie[26];
        ids = new ArrayList<>();
    }
}