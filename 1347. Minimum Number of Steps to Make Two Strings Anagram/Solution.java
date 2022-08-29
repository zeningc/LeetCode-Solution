class Solution {
    public int minSteps(String s, String t) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        
        for (char c : s.toCharArray())
            freq1[c - 'a']++;
        for (char c : t.toCharArray())
            freq2[c - 'a']++;
        
        int ans = 0;
        
        for (int i = 0; i < 26; i++)
            ans += Math.abs(freq1[i] - freq2[i]);
        
        return ans / 2;
    }
}
