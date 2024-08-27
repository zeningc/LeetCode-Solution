class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        int[][] pre = new int[n][26];
        for (int i = 0; i < n; i++)
            Arrays.fill(pre[i], -1);
        for (int i = 0; i < n - 1; i++)    {
            for (int j = 0; j < 26; j++)    {
                pre[i + 1][j] = pre[i][j];
            }
            pre[i + 1][s.charAt(i) - 'A'] = i;
        }
        
        int[][] nxt = new int[n][26];
        for (int i = 0; i < n; i++)
            Arrays.fill(nxt[i], n);
        for (int i = n - 1; i > 0; i--)    {
            for (int j = 0; j < 26; j++)    {
                nxt[i - 1][j] = nxt[i][j];
            }
            nxt[i - 1][s.charAt(i) - 'A'] = i;
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int left = pre[i][s.charAt(i) - 'A'];
            int right = nxt[i][s.charAt(i) - 'A'];
            ans += (i - left) * (right - i);
        }
        
        return ans;
    }
}