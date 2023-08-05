class Solution {
    public int maxIncreasingCells(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        List<int[]> list = new ArrayList<>(m * n);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                list.add(new int[] {mat[i][j], i, j});
            }
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        int[] row = new int[m];
        int[] column = new int[n];
        int[][] dp = new int[m][n];
        int ans = 1;
        int preVal = Integer.MAX_VALUE;
        Map<Integer, Integer> rowTemp = new HashMap<>();
        Map<Integer, Integer> columnTemp = new HashMap<>();
        for (int i = m * n - 1; i >= 0; i--)    {
            int[] cur = list.get(i);
            int curVal = cur[0];
            int x = cur[1];
            int y = cur[2];
            if (preVal != curVal)   {
                for (Map.Entry<Integer, Integer> e : rowTemp.entrySet())    {
                    int k = e.getKey();
                    int v = e.getValue();
                    row[k] = Math.max(row[k], v);
                }
                for (Map.Entry<Integer, Integer> e : columnTemp.entrySet()) {
                    int k = e.getKey();
                    int v = e.getValue();
                    column[k] = Math.max(column[k], v);
                }
                rowTemp.clear();
                columnTemp.clear();
            }
            dp[x][y] = Math.max(row[x] + 1, column[y] + 1);
            ans = Math.max(ans, dp[x][y]);
            rowTemp.put(x, Math.max(rowTemp.getOrDefault(x, 0), dp[x][y]));
            columnTemp.put(y, Math.max(columnTemp.getOrDefault(y, 0), dp[x][y]));
            preVal = curVal;
        }
        return ans;
    }
}