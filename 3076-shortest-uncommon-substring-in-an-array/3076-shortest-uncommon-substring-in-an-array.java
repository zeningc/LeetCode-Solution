class Solution {
    public String[] shortestSubstrings(String[] arr) {
        Trie root = new Trie('#');
        String[] ans = new String[arr.length];
        for (String a : arr)    {
            for (int i = 0; i < a.length(); i++)    {
                Trie node = root;
                for (int j = i; j < a.length(); j++)    {
                    if (node.children[a.charAt(j) - 'a'] == null)
                        node.children[a.charAt(j) - 'a'] = new Trie(a.charAt(j));
                    node = node.children[a.charAt(j) - 'a'];
                    node.cnt++;
                }
            }
        }
        
        for (int k = 0; k < arr.length; k++)    {
            String a = arr[k];
            for (int i = 0; i < a.length(); i++)    {
                Trie node = root;
                for (int j = i; j < a.length(); j++)    {
                    node = node.children[a.charAt(j) - 'a'];
                    node.cnt--;
                }
            }
            
            String curAns = null;
            
            for (int i = 0; i < a.length(); i++)    {
                Trie node = root;
                for (int j = i; j < a.length(); j++)    {
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
            
            
            for (int i = 0; i < a.length(); i++)    {
                Trie node = root;
                for (int j = i; j < a.length(); j++)    {
                    if (node.children[a.charAt(j) - 'a'] == null)
                        node.children[a.charAt(j) - 'a'] = new Trie(a.charAt(j));
                    node = node.children[a.charAt(j) - 'a'];
                    node.cnt++;
                }
            }
            
            ans[k] = curAns == null ? "" : curAns;
        }
        
        return ans;
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