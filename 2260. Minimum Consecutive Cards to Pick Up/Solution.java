class Solution {
    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length; i++)  {
            int card = cards[i];
            if (map.containsKey(card)) {
                ans = Math.min(ans, i - map.get(card) + 1);
            }
            map.put(card, i);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
