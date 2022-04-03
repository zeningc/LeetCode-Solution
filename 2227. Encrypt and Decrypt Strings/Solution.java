class Encrypter {
    Map<Character, Integer> c2i;
    Map<String, List<Integer>> v2i;
    Trie root;
    char[] keys;
    String[] values;
    int cnt;
    public Encrypter(char[] keys, String[] values, String[] dictionary) {
        this.keys = keys;
        this.values = values;
        c2i = new HashMap<>();
        for (int i = 0; i < keys.length; i++)   
            c2i.put(keys[i], i);
        v2i = new HashMap<>();
        
        for (int i = 0; i < values.length; i++)   {
            String val = values[i];
            if (!v2i.containsKey(val))
                v2i.put(val, new LinkedList<>());
            v2i.get(val).add(i);
        }
        
        root = new Trie();
        for (String d : dictionary)
            addWord(root, d);
    }
    
    public String encrypt(String word1) {
        StringBuilder sb = new StringBuilder();
        for (char c : word1.toCharArray())  {
            sb.append(values[c2i.get(c)]);
        }
        return sb.toString();
    }
    
    public int decrypt(String word2) {
        cnt = 0;
        dfs(root, word2, 0);
        return cnt;
    }
    
    void dfs(Trie root, String ref, int index) {
        if (index >= ref.length())  {
            if (root.isWord)
                cnt++;
            return;
        }
        
        List<Integer> iList = v2i.getOrDefault(ref.substring(index, index + 2), null);
        if (iList == null)
            return;
        for (int i : iList)  {
            if (root.children[keys[i] - 'a'] == null)
                continue;
            dfs(root.children[keys[i] - 'a'], ref, index + 2);
        }
        return;
    }
    
    void addWord(Trie root, String s) {
        for (char c : s.toCharArray())  {
            if (root.children[c - 'a'] == null)
                root.children[c - 'a'] = new Trie();
            root = root.children[c - 'a'];
        }
        root.isWord = true;
    }
}


class Trie  {
    Trie[] children;
    boolean isWord;
    public Trie()   {
        children=new Trie[27];
        isWord = false;
    }
}
