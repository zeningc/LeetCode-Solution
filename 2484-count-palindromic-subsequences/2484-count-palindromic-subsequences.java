class Solution {
    public int countPalindromes(String s) {
        char[] ch = s.toCharArray();
        int n = s.length();
        
        int[][] freqTail = new int[n][100];
        int[][] freqHead = new int[n][100];
        for (int i = 0; i < 100; i++)   {
            int x = i / 10;
            int y = i % 10;
            for (int j = 0; j < n; j++) {
                if (ch[j] - '0' != x)   {
                    continue;
                }
                for (int k = j + 1; k < n; k++) {
                    if (ch[k] - '0' == y)   {
                        freqTail[k][i]++;
                        freqHead[j][i]++;
                    }
                }
            }
        }
        
        for (int i = 0; i < 100; i++)   {
            for (int j = 0; j < n; j++) {
                freqTail[j][i] = (j == 0 ? 0 : freqTail[j - 1][i]) + freqTail[j][i];
            }
        }
        
        for (int i = 0; i < 100; i++)   {
            for (int j = n - 1; j >= 0; j--) {
                freqHead[j][i] = (j == n - 1 ? 0 : freqHead[j + 1][i]) + freqHead[j][i];
            }
        }
        
        long ans = 0;
        int mod = (int)1e9 + 7;
        for (int i = 2; i < n - 2; i++) {
            for (int j = 0; j < 100; j++)   {
                int x = j / 10;
                int y = j % 10;
                int r = y * 10 + x;
                ans = (ans + (long)freqTail[i - 1][j] * freqHead[i + 1][r]) % mod;
            }
        }
        
        return (int)(ans % mod);
    }
}