class Solution {
    int ans;
    boolean[] vis;
    int n;
    int len;
    long[] ten;
    public int countSpecialNumbers(int n) {
        if (n == 0)
            return 0;
        ten = new long[11];
        ten[0] = 1;
        for (int i = 1; i <= 10; i++)
            ten[i] = ten[i - 1] * 10;
        ans = 0;
        this.n = n;
        len = 0;
        int t = n;
        while (t != 0)  {
            t /= 10;
            len++;
        }
        vis = new boolean[10];
        dfs(0L, 1);
        return ans - 1;
    }
    
    void dfs(long num, int digit)   {
        if (digit > len)  {
            if (num <= n)   {
                ans++;
            }
            return;
        }
        
        if (num * ten[len - digit + 1] > n)
            return;
        
        if (num == 0)
            dfs(num * 10, digit + 1);
        
        for (int i = 0; i <= 9; i++)    {
            if (i == 0 && num == 0)
                continue;
            if (vis[i])
                continue;
            vis[i] = true;
            dfs(num * 10 + i, digit + 1);
            vis[i] = false;
        }
    }
}
