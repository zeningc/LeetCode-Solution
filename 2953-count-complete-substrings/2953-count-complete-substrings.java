class Solution {
    public int countCompleteSubstrings(String word, int k) {
        int lo = 0;
        int n = word.length();
        int[][] pos = new int[26][n + 1];
        int ans = 0;
        for (int hi = 0; hi < n; hi++)    {
            char c = word.charAt(hi);
            for (int i = 0; i < 26; i++)
                pos[i][hi + 1] = pos[i][hi];
            pos[c - 'a'][hi + 1]++;
            
            if (hi == 0 || Math.abs(c - word.charAt(hi - 1)) > 2)   {
                lo = hi;
            }
            if (pos[c - 'a'][hi + 1] - pos[c - 'a'][lo] >= k) {
                for (int i = 1; i <= 26; i++)   {
                    int start = hi - i * k + 1;
                    if (start < lo)
                        break;
                    boolean valid = true;
                    for (int j = 0; j < 26; j++)   {
                        if (pos[j][hi + 1] - pos[j][start] != 0 && pos[j][hi + 1] - pos[j][start] != k)    {
                            valid = false;
                            break;
                        }
                    }
                    if (valid)  {
                        ans++;
                    }
                        
                }
            }
            
        }
        
        return ans;
    }
}