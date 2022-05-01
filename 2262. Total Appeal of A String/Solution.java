class Solution {
    public long appealSum(String s) {
        long ans = 0;
        int n = s.length();
        int[][] prev = new int[26][n];
        for (int i = 0; i < 26; i++)
            prev[i][0] = -1;
        
        boolean[] visited = new boolean[26];
        
        for (int i = 0; i < n - 1; i++) {
            char c = s.charAt(i);
            for (int j = 0; j < 26; j++)    {
                prev[j][i + 1] = prev[j][i];
            }
            prev[c - 'a'][i + 1] = i;
        }
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            ans += (long)(i - prev[c - 'a'][i]) * (n - i);
        }
        
        return ans;
    }
}
