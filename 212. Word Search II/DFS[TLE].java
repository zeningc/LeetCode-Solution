class Solution {
    Set<String> ansSet;
    char[][] board;
    boolean[][] visited;
    int m;
    int n;
    int[][] dir = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        this.board = board;
        ansSet = new HashSet<>();
        visited = new boolean[m][n];
        List<String> wordList = new ArrayList<>(words.length);
        
        for (String word : words)
            wordList.add(word);
        
        for (int k = 0; k < wordList.size(); k++)    {
            boolean flag = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (dfs(wordList.get(k), 0, i, j))  {
                        flag = true;
                        ansSet.add(wordList.get(k));
                        break;
                    }
                }
                if (flag)
                    break;
            }
        }
        
        List<String> ansList = new LinkedList<>();
        for (String word : ansSet)
            ansList.add(word);
        
        return ansList;
    }
    
    boolean dfs(String word, int idx, int x, int y)   {
        char c = board[x][y];
        if (c != word.charAt(idx))
            return false;
        
        if (idx == word.length() - 1)
            return true;
        
        visited[x][y] = true;
        for (int[] d : dir) {
            int nx = x + d[0];
            int ny = y + d[1];
            if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1)
                continue;
            if (visited[nx][ny])
                continue;
            if (dfs(word, idx + 1, nx ,ny)) {
                visited[x][y] = false;
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }
    
}
