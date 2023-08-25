class Solution {
    public long matrixSumQueries(int n, int[][] queries) {
        long ans = 0;
        boolean[] rowVis = new boolean[n];
        boolean[] colVis = new boolean[n];
        int colCnt = 0;
        int rowCnt = 0;
        for (int i = queries.length - 1; i >= 0; i--)   {
            int[] query = queries[i];
            int type = query[0];
            int index = query[1];
            int val = query[2];
            if (type == 0)  {
                if (rowVis[index])
                    continue;
                ans += (long)val * (n - colCnt);
                rowVis[index] = true;
                rowCnt++;
            }
            else if (type == 1) {
                if (colVis[index])
                    continue;
                ans += (long)val * (n - rowCnt);
                colVis[index] = true;
                colCnt++;
            }
        }
        return ans;
    }
}