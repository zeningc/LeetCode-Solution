class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase().replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray())  {
            if (c >= 'a' && c <= 'z' || c >= '0' && c <= '9')
                sb.append(c);
        }
        s = sb.toString();
        int p = 0;
        int q = s.length() - 1;
        while (p < q)   {
            if (s.charAt(p) != s.charAt(q))
                return false;
            p++;q--;
        }
        return true;
    }
}
