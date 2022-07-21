class Solution {
    int[][] mem;
    public int uniquePaths(int m, int n) {
        if (m == 1)
            return 1;
        if (n == 1)
            return 1;
        mem = new int[m][m + n];
        return getComb(m - 1, m + n - 2);
    }
    
    int getComb(int m, int n)  {
        if (n > m && n - m < m)
            return getComb(n - m, n);
        if (mem[m][n] != 0)
            return mem[m][n];
        if (m == 1)
            return n;
        if (m == n)
            return 1;
        mem[m][n] = getComb(m - 1, n - 1) + getComb(m, n - 1);
        return mem[m][n];
    }
}
