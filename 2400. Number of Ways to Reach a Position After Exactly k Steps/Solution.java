class Solution {
    int mod = (int)1e9 + 7;
    public int numberOfWays(int startPos, int endPos, int k) {
        int gap = Math.abs(endPos - startPos);
        
        if (gap > k)
            return 0;
        if (gap == k)
            return 1;
        
        if ((k + gap) % 2 == 1)
            return 0;
        
        int y = (k + gap) / 2;
        
        return c(k, y);
    }
    
    int c(int n, int k) {
        long[][] comb = new long[1001][1001];  
        for (int i = 0; i <= n; ++i)    {
              comb[i][i] = 1;
              comb[i][0] = 1;            
              if (i==0) continue;
              for (int j = 1; j < i; ++j)   {
                  comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % mod;
              }
          }
        
        return (int)(comb[n][k] % mod);
    }
}
