class Solution {
    Set<String> wordSet;
    Set<String> ansSet;
    char[][] board;
    boolean[][] visited;
    int m;
    int n;
    Trie root;
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        root = new Trie();
        wordSet = new HashSet<>();
        ansSet = new HashSet<>();
        visited = new boolean[m][n];
        for (String word : words){
            wordSet.add(word);
            insert(root, word);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(root, i, j);
            }
        }
        
        List<String> ansList = new LinkedList<>();
        for (String word : ansSet)
            ansList.add(word);
        
        return ansList;
    }
    
    void dfs(Trie node, int x, int y)   {
        char c = board[x][y];
        if (node.children[c - 'a'] == null)
            return;
        node = node.children[c - 'a'];
        if (node.isWord)
            ansSet.add(node.word);
        visited[x][y] = true;
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                continue;
            if (visited[nx][ny])
                continue;
            dfs(node, nx, ny);
        }
        visited[x][y] = false;
    }
    
    void insert(Trie root, String word) {
        Trie node = root;
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
    String word;
    boolean isWord;
    Trie[] children;
    
    public Trie()   {
        children = new Trie[26];
        isWord = false;
        word = null;
    }
}
