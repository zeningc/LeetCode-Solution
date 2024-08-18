class Solution {
    public String largestPalindrome(int n, int k) {
        int[][] kMod = new int[n][10];
        Boolean[][] cache = new Boolean[n][10];
        int pow = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < 10; j++)
                kMod[i][j] = (j * pow) % k;
            pow = (pow * 10) % k;
        }
        
        int[] ans = new int[n];
        dfs(cache, ans, kMod, 0, (n - 1) / 2, n, 0, k);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append((char)('0' + ans[i]));
        
        return sb.toString();
    }
    
    boolean dfs(Boolean[][] cache, int[] ans, int[][] kMod, int idx, int n, int t, int mod, int k) {
        if (idx > n)
            return mod == 0;
        if(cache[idx][mod] != null)
            return cache[idx][mod];
        
        int sym = t - idx - 1;
        for (int d = 9; d >= 1; d--)    {
            if (dfs(cache, ans, kMod, idx + 1, n, t, (mod + kMod[idx][d] + (sym == idx ? 0 : kMod[sym][d])) % k, k))   {
                ans[idx] = d;
                ans[sym] = d;
                cache[idx][mod] = true;
                return true;
            }
        }
        cache[idx][mod] = false;
        return false;
    }
}