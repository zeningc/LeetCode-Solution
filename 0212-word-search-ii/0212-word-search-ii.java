class Solution {
    int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> ansSet = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        Trie root = new Trie('#');
        for (String word : words)   {
            Trie node = root;
            for (char c : word.toCharArray())   {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie(c);
                }
                node = node.children[c - 'a'];
            }
            node.exist = true;
            node.word = word;
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(ansSet, board, new boolean[m][n], m, n, i, j, root);
            }
        }
        
        return new ArrayList<>(ansSet);
    }
    
    void dfs(Set<String> ansSet, char[][] board, boolean[][] vis, int m, int n, int i, int j, Trie node)  {
        char c = board[i][j];
        if (node.children[c - 'a'] == null)
            return;
        node = node.children[c - 'a'];
        if (node.exist) {
            ansSet.add(node.word);
        }
        vis[i][j] = true;
        for (int[] d : dir) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni < 0 || ni >= m || nj < 0 || nj >= n)
                continue;
            if (vis[ni][nj])
                continue;
            dfs(ansSet, board, vis, m, n, ni, nj, node);
           
        }
        vis[i][j] = false;
    }
}
class Trie  {
    char c;
    Trie[] children;
    boolean exist;
    String word;
    public Trie(char c)   {
        this.c = c;
        children = new Trie[26];
    }
}