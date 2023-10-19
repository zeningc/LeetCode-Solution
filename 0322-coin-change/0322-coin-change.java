class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int coin : coins)
            for (int j = 0; j <= amount; j++)
                if (j >= coin)
                    dp[j] = Math.min(dp[j], dp[j - coin] + 1);
        
        return dp[amount] >= Integer.MAX_VALUE / 2 ? -1 : dp[amount];
    }
}


