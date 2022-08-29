class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        int cnt = 0;
        char a = '#';
        char b = '#';
        for (int i = 0; i < s1.length(); i++)   {
            if (s1.charAt(i) != s2.charAt(i))   {
                cnt++;
                if (cnt > 2)
                    return false;
                if (cnt == 2)   {
                    if (a == s2.charAt(i) && b == s1.charAt(i))
                        continue;
                    return false;
                }
                
                if (cnt == 1)   {
                    a = s1.charAt(i);
                    b = s2.charAt(i);
                }
            }
        }
        
        return cnt == 2 || cnt == 0;        
    }
}
