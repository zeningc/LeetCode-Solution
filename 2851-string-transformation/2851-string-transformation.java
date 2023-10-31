class Solution {
    int MOD = (int)1e9 + 7;
    public int numberOfWays(String s, String t, long k) {
        int p = kmp(t, s + s.substring(0, s.length() - 1));
        int n = s.length();
        long[][] matrix = quickPow(new long[][] {{n - p - 1, n - p}, {p, p - 1}}, k);
        long[][] initialMatrix = s.compareTo(t) == 0 ? new long[][] {{0}, {1}} : new long[][] {{1}, {0}};
        return (int)(matrixMultiple(matrix, initialMatrix)[1][0] % MOD);
    }
    
    long[][] quickPow(long[][] matrix, long n)
    {
        if (n == 0)
            return new long[][] {{1, 0}, {0, 1}};
        long[][] mid = quickPow(matrix, n / 2);
        if (n % 2 == 0)
            return matrixMultiple(mid, mid);
        return matrixMultiple(matrixMultiple(mid, mid), matrix);
    }
    
    long[][] matrixMultiple(long[][] a, long[][] b)
    {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        long[][] ret = new long[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    ret[i][j] = (ret[i][j] + a[i][k] * b[k][j]) % MOD;
        
        return ret;
    }
    
    int kmp(String t, String s)
    {
        int m = t.length();
        int n = s.length();
        String concat = t + "#" + s;
        int[] pi = prefixFunction(concat);
        int cnt = 0;
        for (int i = m + 1; i < m + n + 1; i++)
            if (pi[i] == m)
                cnt++;
        return cnt;
    }
    
    int[] prefixFunction(String s)
    {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++)
        {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = pi[j - 1];
            
            if (s.charAt(j) == s.charAt(i))
                j++;
            pi[i] = j;
        }
        return pi;
    }
}