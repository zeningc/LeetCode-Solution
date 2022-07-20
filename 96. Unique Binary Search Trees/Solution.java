class Solution {
    int[][] mem;
    public int numTrees(int n) {
        mem = new int[n + 1][n + 1];
        return dfs(1, n);
    }
    
    int dfs(int lo, int hi) {
        if (hi < lo)
            return 1;
        if (mem[lo][hi] != 0)
            return mem[lo][hi];
        int cnt = 0;
        for (int i = lo; i <= hi; i++)  {
            int left = dfs(lo, i - 1);
            int right = dfs(i + 1, hi);
            cnt += left * right;
        }
        mem[lo][hi] = cnt;
        return cnt;
    }
}
