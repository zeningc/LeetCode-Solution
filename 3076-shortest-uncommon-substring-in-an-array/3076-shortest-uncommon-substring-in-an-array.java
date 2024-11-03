class Solution {
    public String[] shortestSubstrings(String[] arr) {
        Trie root = new Trie('#');
        for (String a : arr)
            updateWord(root, a, true);
        
        
        String[] ans = new String[arr.length];
        for (int i = 0; i < arr.length; i++)    {
            String s = arr[i];
            updateWord(root, s, false);
            String curAns = null;
            for (int j = 0; j < s.length(); j++)    {
                Trie node = root;
                for (int k = j; k < s.length(); k++)    {
                    node = node.children[s.charAt(k) - 'a'];
                    if (node.cnt == 0 && (curAns == null || curAns.length() > node.s.length() || curAns.length() == node.s.length() && curAns.compareTo(node.s) > 0))  {
                        curAns = node.s;
                    }
                }
            }
            ans[i] = curAns == null ? "" : curAns;
            updateWord(root, s, true);
        }
        
        return ans;
    }
    
    void updateWord(Trie root, String s, boolean isAdd) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            Trie node = root;
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) {
                if (node.children[s.charAt(j) - 'a'] == null)
                    node.children[s.charAt(j) - 'a'] = new Trie(s.charAt(j));
                node = node.children[s.charAt(j) - 'a'];
                sb.append(s.charAt(j));
                node.cnt = isAdd ? node.cnt + 1 : node.cnt - 1;
                if (node.s == null)
                    node.s = sb.toString();
            }
        }
    }
}

class Trie  {
    char c;
    Trie[] children;
    int cnt;
    String s;
    public Trie(char c) {
        this.c = c;
        children = new Trie[26];
    }
}   