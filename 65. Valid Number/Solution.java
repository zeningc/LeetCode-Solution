class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int idx = 0;
        boolean valid = false;
        boolean hasSign = false;
        boolean hasDigit = false;
        boolean hasDot = false;
        boolean hasE = false;
        while (idx < n) {
            char c = s.charAt(idx);
            if (isSign(c)) {
                valid = false;
                if (hasSign)
                    return false;
                hasSign = true;
                if (hasDigit)
                    return false;
                idx++;
            }
            else if (isDigit(c))    {
                valid = true;
                int j = idx + 1;
                while (j < n && isDigit(s.charAt(j)))
                    j++;
                idx = j;
                hasDigit = true;
            }
            else if (isE(c))    {
                valid = false;
                if (hasE)
                    return false;
                hasE = true;
                if (!hasDigit)
                    return false;
                hasDigit = false;
                hasSign = false;
                idx++;
            }
            else if (c == '.')  {
                if (hasDigit)
                    valid = true;
                else
                    valid = false;
                if (!hasDigit && (idx == n - 1 || !isDigit(s.charAt(idx + 1))))
                    return false;
                if (hasDot)
                    return false;
                hasDot = true;
                if (hasE)
                    return false;
                idx++;
            }
            else
                return false;
        }
        return valid;
    }
    
    boolean isDigit(char c) {return c >= '0' && c <= '9';}
    boolean isE(char c) {return c == 'E' || c == 'e';}
    boolean isSign(char c) {return c == '+' || c == '-';}
}
