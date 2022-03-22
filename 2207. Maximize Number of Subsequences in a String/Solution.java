class Solution {
    public long maximumSubsequenceCount(String text, String pattern) {
        char a = pattern.charAt(0);
        char b = pattern.charAt(1);
        long ans = 0;
        int freqB = 0;
        int freq = 0;
        for (char c : text.toCharArray())   {
            if (c == a) {
                freq++;
            }
            else if (c == b)    {
                freqB++;
                ans += freq;
            }
        }
        
        if (a == b) {
            return (long)freq * (freq + 1) / 2;
        }
        
        return Math.max(freq + ans, freqB + ans);
    }
}

// b * freqa[:i]

