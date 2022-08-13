class Solution {
    public int longestIdealString(String s, int k) {
        int[] len = new int[26];
        int ans = 1;
        for (char c : s.toCharArray())  {
            int curr = 0;
            for (int i = Math.max(0, c - 'a' - k); i <= Math.min(25, c - 'a' + k); i++)   {
                curr = Math.max(len[i] + 1, curr);
            }
            len[c - 'a'] = curr;
        }
        
        for (int i = 0; i < 26; i++)
            ans = Math.max(ans, len[i]);
        return ans;
    }
}
