class Solution {
    public int minimumAddedCoins(int[] coins, int target) {
        int ans = 0;
        long r = 0;
        Arrays.sort(coins);
        for (int coin : coins)  {
            while (coin > r + 1 && r < target)  {
                ans++;
                r += (r + 1);
            }
            
            if (coin <= r + 1)
                r += coin;
            
            if (r >= target)
                break;
        }
        
        while (r < target)  {
            ans++;
            r += (r + 1);
        }
        
        return ans;
    }
}