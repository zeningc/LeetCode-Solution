class Solution {
    public int numberOfSubstrings(String s, int k) {
        int cnt = 0;
        int lo = 0;
        int ans = 0;
        Map<Character, Integer> freq = new HashMap<>();
        for (int hi = 0; hi < s.length(); hi++) {
            char c = s.charAt(hi);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            if (freq.get(c) == k)
                cnt++;
            while (cnt > 0) {
                freq.put(s.charAt(lo), freq.get(s.charAt(lo)) - 1);
                if (freq.get(s.charAt(lo)) == k - 1)
                    cnt--;
                if (freq.get(s.charAt(lo)) == 0)
                    freq.remove(s.charAt(lo));
                lo++;
            }
            
            ans += lo;
        }
        
        return ans;
    }
}