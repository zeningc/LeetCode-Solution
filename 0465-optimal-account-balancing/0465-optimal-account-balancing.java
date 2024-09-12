class Solution {
    int ans = Integer.MAX_VALUE;
    public int minTransfers(int[][] transactions) {
        int[] balance = new int[12];
        for (int[] transaction : transactions)  {
            int from = transaction[0];
            int to = transaction[1];
            int amount = transaction[2];
            balance[from] += amount;
            balance[to] -= amount;
        }
        
        dfs(balance, 0, 0);
        return ans;
    }
    
    
    void dfs(int[] balance, int start, int cnt)  {
        if (start >= balance.length)    {
            ans = Math.min(ans, cnt);
            return;
        }
        
        if (balance[start] == 0)    {
            dfs(balance, start + 1, cnt);
            return;
        }
        
        for (int i = start + 1; i < balance.length; i++)    {
            if (balance[i] > 0 && balance[start] < 0 || balance[i] < 0 && balance[start] > 0)   {
                balance[i] += balance[start];
                dfs(balance, start + 1, cnt + 1);
                balance[i] -= balance[start];
            }
        }
    }
}

/*

dp[i][j]


[a, b, c]
[d, e, f]
=> [1, -2, 1]


[5, -10, 5]
[5, 5, -10]

[negative, positive]
negative [bit -> state]

0: +10 -1 -5 = 4
1: -10 + 1 + 5 = -4
2 -5 + 5 = 0




*/