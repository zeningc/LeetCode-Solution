class Solution {
    public String[] shortestSubstrings(String[] arr) {
        Trie root = new Trie('#');
        String[] ans = new String[arr.length];
        for (String a : arr)    {
            updateWord(root, a, true);
        }
        
        for (int k = 0; k < arr.length; k++)    {
            String a = arr[k];
            updateWord(root, a, false);
            
            String curAns = null;
            
            for (int i = 0; i < a.length(); i++)    {
                Trie node = root;
                for (int j = i; j < a.length(); j++)    {
                    if (curAns != null && (j - i + 1) > curAns.length())
                        break;
                    if (node.children[a.charAt(j) - 'a'] == null || node.children[a.charAt(j) - 'a'].cnt == 0)  {
                        String sub = a.substring(i, j + 1);
                        if (curAns == null || curAns.length() > (j - i + 1) || curAns.length() == (j - i + 1) && curAns.compareTo(sub) > 0)   {
                            curAns = sub;
                        }
                        break;
                    }
                    node = node.children[a.charAt(j) - 'a'];
                }
            }
            
            updateWord(root, a, true);
            
            
            ans[k] = curAns == null ? "" : curAns;
        }
        
        return ans;
    }
    
    void updateWord(Trie root, String s, boolean isAdd) {
        for (int i = 0; i < s.length(); i++)    {
            Trie node = root;
            for (int j = i; j < s.length(); j++)    {
                if (node.children[s.charAt(j) - 'a'] == null)
                    node.children[s.charAt(j) - 'a'] = new Trie(s.charAt(j));
                node = node.children[s.charAt(j) - 'a'];
                if (isAdd)
                    node.cnt++;
                else node.cnt--;
            }
        }
    }
}

class Trie  {
    char c;
    Trie[] children;
    int cnt;
    
    public Trie(char c) {
        this.c = c;
        children = new Trie[26];
        cnt = 0;
    }
}