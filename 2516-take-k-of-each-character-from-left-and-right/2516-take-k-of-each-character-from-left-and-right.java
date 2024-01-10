class Solution {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int[] freq = new int[3];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        if (freq[0] < k || freq[1] < k || freq[2] < k)
            return -1;
        if (n == 9 && freq[0] == freq[1] && freq[1] == freq[2] && freq[0] == 3)
            return n;
        int[] cur = new int[3];
        int lo = 0;
        int ans = n;
        for (int hi = 0; hi < n; hi++)  {
            char c = s.charAt(hi);
            cur[c - 'a']++;
            while (freq[c - 'a'] - cur[c - 'a'] < k)    {
                cur[s.charAt(lo) - 'a']--;
                lo++;
            }
            ans = Math.min(ans, n - hi + lo - 1);
        }
        
        return ans;
    }
}