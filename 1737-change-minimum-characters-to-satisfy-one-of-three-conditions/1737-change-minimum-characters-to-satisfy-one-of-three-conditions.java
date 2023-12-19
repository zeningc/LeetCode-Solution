class Solution {
    public int minCharacters(String a, String b) {
        int[][] dp1 = new int[26][2];
        int[][] dp2 = new int[26][2];
        int[] distinctA = new int[26];
        int[] distinctB = new int[26];
        for (int i = 0; i < 26; i++)    {
            int lt = 0;
            int st = 0;
            int d = 0;
            for (int j = 0; j < a.length(); j++)    {
                if (a.charAt(j) - 'a' <= i)
                    lt++;
                if (a.charAt(j) - 'a' > i)
                    st++;
                if (a.charAt(j) - 'a' != i)
                    d++;
            }
            dp1[i][0] = st;
            dp1[i][1] = lt;
            distinctA[i] = d;
            lt = 0;
            st = 0;
            d = 0;
            for (int j = 0; j < b.length(); j++)    {
                if (b.charAt(j) - 'a' <= i)
                    lt++;
                if (b.charAt(j) - 'a' > i)
                    st++;
                if (b.charAt(j) - 'a' != i)
                    d++;
            }
            dp2[i][0] = st;
            dp2[i][1] = lt;
            distinctB[i] = d;
        }
        dp1[25][1] = Integer.MAX_VALUE / 2;
        dp2[25][1] = Integer.MAX_VALUE / 2;
        
        int ans = a.length() + b.length();
        
        for (int i = 0; i < 26; i++)    {
                ans = Math.min(ans, dp1[i][0] + dp2[i][1]);
                ans = Math.min(ans, dp1[i][1] + dp2[i][0]);
            for (int j = 0; j < 26; j++)    {
                ans = Math.min(ans, distinctA[i] + distinctB[j]);
            }
            if (ans == 1)   {
                System.out.println(i);
            }
        }
        
        return ans;
    }
}