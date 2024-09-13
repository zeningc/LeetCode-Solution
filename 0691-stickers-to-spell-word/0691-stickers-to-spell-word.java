class Solution {
    Map<String, int[]> stickerFreqMap;
    public int minStickers(String[] stickers, String target) {
        stickerFreqMap = new HashMap<>();
        for (String sticker : stickers) {
            int[] freq = new int[26];
            for (char c : sticker.toCharArray())
                freq[c - 'a']++;
            stickerFreqMap.put(sticker, freq);
        }
            
        int n = target.length();
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int state = 0; state < (1 << n); state++)  {
            for (String sticker : stickers) {
                int newState = calc(sticker, state, target);
                dp[newState] = Math.min(dp[newState], dp[state] + 1);
            }
        }
        
        return dp[(1 << n) - 1] >= Integer.MAX_VALUE / 2 ? -1 : dp[(1 << n) - 1];
    }
    
    int calc(String sticker, int state, String target)  {
        int[] freq = stickerFreqMap.get(sticker).clone();
        for (int i = 0; i < target.length(); i++)   {
            if (((1 << i) & state) == 0 && freq[target.charAt(i) - 'a'] > 0)    {
                freq[target.charAt(i) - 'a']--;
                state |= (1 << i);
            }
        }
        return state;
    }
}