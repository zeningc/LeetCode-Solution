class Solution {
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        String t = new StringBuilder(s.substring(s.length() / 2, s.length())).reverse().toString();
        s = s.substring(0, s.length() / 2);
        int n = s.length();
        int[][] presum1 = new int[26][n + 1];
        int[][] presum2 = new int[26][n + 1];
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++)    {
                presum1[j][i + 1] = presum1[j][i];
                presum2[j][i + 1] = presum2[j][i];
            }
            presum1[s.charAt(i) - 'a'][i + 1] += 1;
            presum2[t.charAt(i) - 'a'][i + 1] += 1;
            diff[i + 1] = diff[i] + (s.charAt(i) != t.charAt(i) ? 1 : 0);
        }
        
        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++)    {
            int a = queries[i][0];
            int b = queries[i][1];
            int d = 2 * n - queries[i][2] - 1;
            int c = 2 * n - queries[i][3] - 1;
            boolean valid = true;
            int[] count1 = new int[26];
            int[] count2 = new int[26];
            for (int j = 0; j < 26; j++)    {
                count1[j] = presum1[j][b + 1] - presum1[j][a];
                count2[j] = presum2[j][d + 1] - presum2[j][c];
            }
            
            int[][] nonUnions = new int[][] {{0, Math.min(a, c) - 1}, {Math.max(b, d) + 1, n - 1}, {b + 1, c - 1}, {d + 1, a - 1}};
            for (int[] u : nonUnions)   {
                if (u[0] > u[1])
                    continue;
                if ((diff[u[1] + 1] - diff[u[0]]) != 0) {
                    valid = false;
                    break;
                }
            }
            
            if (!valid) {
                ans[i] = false;
                continue;
            }
            
            
            int[][] As = new int[][] {{a, Math.min(b, c - 1)}, {Math.max(d + 1, a), b}};
            for (int[] u : As)  {
                if (u[0] > u[1])
                    continue;
                for (int j = 0; j < 26; j++)    {
                    count1[j] -= (presum2[j][u[1] + 1] - presum2[j][u[0]]);
                    if (count1[j] < 0)  {
                        valid = false;
                        break;
                    }
                }
                if (!valid)
                    break;
            }
            
            if (!valid) {
                ans[i] = false;
                continue;
            }
            
            int[][] Bs = new int[][] {{c, Math.min(d, a - 1)}, {Math.max(b + 1, c), d}};
            for (int[] u : Bs)  {
                if (u[0] > u[1])
                    continue;
                for (int j = 0; j < 26; j++)    {
                    count2[j] -= (presum1[j][u[1] + 1] - presum1[j][u[0]]);
                    if (count2[j] < 0)  {
                        valid = false;
                        break;
                    }
                }
                if (!valid)
                    break;
            }
            
            if (!valid) {
                ans[i] = false;
                continue;
            }
            
            int[] intersect = new int[] {Math.max(a, c), Math.min(b, d)};
            if (intersect[0] <= intersect[1])   {
                for (int j = 0; j < 26; j++)    {
                    if (count1[j] != count2[j])    {
                        valid = false;
                        break;
                    }
                    if (!valid)
                        break;
                }
            }
            
            if (!valid) {
                ans[i] = false;
                continue;
            }
            ans[i] = true;
        }
        
        
        return ans;
    }
}



