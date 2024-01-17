class Solution {
    public int getMaximumConsecutive(int[] coins) {
        int r = 0;
        Arrays.sort(coins);
        for (int coin : coins)  {
            if (coin <= r + 1)
                r += coin;
        }
        
        return r + 1;
    }
}