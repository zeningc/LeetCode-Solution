class Solution {
    public long validSubstringCount(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (char c : word2.toCharArray())
            freq2[c - 'a']++;
        int cnt = 0;
        for (int i = 0; i < 26; i++)
            if (freq2[i] == 0)
                cnt++;
        
        long ans = 0;
        int lo = 0;
        for (int hi = 0; hi < word1.length(); hi++) {
            char c = word1.charAt(hi);
            freq1[c - 'a']++;
            if (freq1[c - 'a'] == freq2[c - 'a'])
                cnt++;
            while (cnt == 26)   {
                char loCh = word1.charAt(lo);
                freq1[loCh - 'a']--;
                if (freq1[loCh - 'a'] == freq2[loCh - 'a'] - 1)
                    cnt--;
                lo++;
            }
            ans += lo;
        }
        
        return ans;
    }
}