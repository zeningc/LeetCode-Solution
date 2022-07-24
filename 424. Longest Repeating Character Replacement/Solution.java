class Solution {
    public int characterReplacement(String s, int k) {
        int left = 0;
        int[] freq = new int[26];
        int ans = 1;
        for (int i = 0; i < s.length(); i++)    {
            char c = s.charAt(i);
            freq[c - 'A']++;
            while (!check(freq, k)) {
                freq[s.charAt(left) - 'A']--;
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }
    
    boolean check(int[] freq, int k)   {
        int max = 0;
        int cnt = 0;
        for (int i = 0; i < 26; i++)    {
            if (freq[i] > max)  {
                cnt += max;
                max = freq[i];
            }
            else
                cnt += freq[i];
        }
        
        return cnt <= k;
    }
}
