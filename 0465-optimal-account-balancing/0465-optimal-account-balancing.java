class Solution {
    int ans;
    public int minTransfers(int[][] transactions) {
        ans = Integer.MAX_VALUE;
        int[] account = new int[12];
        for (int[] transaction : transactions)  {
            account[transaction[0]] += transaction[2];
            account[transaction[1]] -= transaction[2];
        }
        
        dfs(0, account, 0);
        
        return ans;
    }
    
    
    void dfs(int idx, int[] account, int cnt)   {
        if (idx >= account.length)  {
            ans = Math.min(ans, cnt);
            return;
        }
        
        if (account[idx] == 0)  {
            dfs(idx + 1, account, cnt);
            return;
        }
        
        for (int i = idx + 1; i < account.length; i++)  {
            if (account[i] * account[idx] >= 0)
                continue;
            account[i] += account[idx];
            dfs(idx + 1, account, cnt + 1);
            account[i] -= account[idx];
        }
    }
}