class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        String ans = s;
        int n = s.length();
        int[] minDigit = new int[10];
        for (int i = 0; i < 10; i++)    {
            int r = (i + a) % 10;
            int min = Math.min(i, r);
            while (r != i)  {
                r = (r + a) % 10;
                min = Math.min(min, r);
            }
            minDigit[i] = min;
        }
        boolean first = true;
        for (int i = n; i != 0 || first; i = (i + n - b) % n)    {
            first = false;
            String cur = s.substring(i) + s.substring(0, i);
            char[] ch = cur.toCharArray();
            while (minDigit[cur.charAt(1) - '0'] != ch[1] - '0')  {
                for (int j = 1; j < n; j += 2) {
                    ch[j] = (char)('0' + (ch[j] - '0' + a) % 10);
                }
            }
            
            if (b % 2 != 0) {
                while (minDigit[cur.charAt(0) - '0'] != ch[0] - '0')  {
                    for (int j = 0; j < n; j += 2) {
                        ch[j] = (char)('0' + (ch[j] - '0' + a) % 10);
                    }
                }
            }
            
            for (int j = 0; j < n; j++) {
                if (ans.charAt(j) == ch[j])
                    continue;
                if (ch[j] < ans.charAt(j))  {
                    ans = new String(ch);
                }
                break;
            }
        }
        
        return ans;
    }
}