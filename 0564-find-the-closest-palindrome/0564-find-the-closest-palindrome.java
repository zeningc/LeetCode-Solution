class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.valueOf(n);
        if (num <= 10)
            return String.valueOf(num - 1);
        if (num == 11)
            return "9";
        if (num <= 16)
            return "11";
        if (num < 20)
            return "22";
        String flipPartStr = n.substring(0, ((n.length() + 1) / 2));
        long flipPart = Long.valueOf(flipPartStr);
            
        long a = flip(flipPart, num, n);
        if (a == num)   {
            if (String.valueOf(flipPart + 1).length() > flipPartStr.length())
                a = Long.valueOf("1" + repeat('0', n.length() - 1) + "1");
            else
                a = flip(flipPart + 1, num, n);
        }
        long b;
        if (a > num)    {
            if (String.valueOf(flipPart - 1).length() < flipPartStr.length())
                b = Long.valueOf(repeat('9', n.length() - 1));
            else
                b = flip(flipPart - 1, num, n);
        }
        else    {
            if (String.valueOf(flipPart + 1).length() > flipPartStr.length())
                b = Long.valueOf("1" + repeat('0', n.length() - 1) + "1");
            else
                b = flip(flipPart + 1, num, n);
        }
        
        return closest(a, b, num);
    }
    
    long flip(long part, long num, String nStr)    {
        String partStr = String.valueOf(part);
        if (partStr.length() * 2 == nStr.length())    {
            return Long.valueOf(partStr + new StringBuilder(partStr).reverse().toString());
        }
        return Long.valueOf(partStr + new StringBuilder(partStr.substring(0, partStr.length() - 1)).reverse().toString());
    }
    
    String repeat(char c, int len)  {
        StringBuilder sb = new StringBuilder();
        while (len-- > 0)
            sb.append(c);
        return sb.toString();
    }
    
    String closest(long a, long b, long num)    {
        long gap1 = Math.abs(a - num);
        long gap2 = Math.abs(b - num);
        if (gap1 == gap2)
            return String.valueOf(Math.min(a, b));
        return String.valueOf(gap1 < gap2 ? a : b);
    }
    
    
    boolean isPalindrome(String n)  {
        int i = 0;
        int j = n.length() - 1;
        while (i < j)   {
            if (n.charAt(i) != n.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}
