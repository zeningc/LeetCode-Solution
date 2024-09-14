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
                if (root.children[board[i][j] - 'a'] == null)
                    continue;
                boolean[][] vis = new boolean[m][n];
                vis[i][j] = true;
                dfs(ansSet, board, vis, m, n, i, j, root.children[board[i][j] - 'a']);
            }
        }
        
        return new ArrayList<>(ansSet);
    }
    
    void dfs(Set<String> ansSet, char[][] board, boolean[][] vis, int m, int n, int i, int j, Trie node)  {
        if (node.exist) {
            ansSet.add(node.word);
        }
        
        for (int[] d : dir) {
            int ni = i + d[0];
            int nj = j + d[1];
            if (ni < 0 || ni >= m || nj < 0 || nj >= n || vis[ni][nj] || node.children[board[ni][nj] - 'a'] == null)
                continue;
            vis[ni][nj] = true;
            dfs(ansSet, board, vis, m, n, ni, nj, node.children[board[ni][nj] - 'a']);
            vis[ni][nj] = false;
        }
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