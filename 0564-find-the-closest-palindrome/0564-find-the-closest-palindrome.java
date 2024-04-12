class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.valueOf(n);
        long a = getNextGreaterPolindrome(num);
        long b = getNextSmallerPolindrome(num);
        if (Math.abs(a - num) < Math.abs(b - num))
            return new String(numToCharArr(a));
        return new String(numToCharArr(b));
    }
    
    long getNextGreaterPolindrome(long num) {
        char[] ch = numToCharArr(num);
        char[] flip = flip(ch);
        if (compare(ch, flip) < 0)
            return charArrToNum(flip);
        int c = 1;
        for (int i = ch.length % 2 == 0 ? (ch.length - 1) / 2 : ch.length / 2; i >= 0; i--)    {
            int bit = ch[i] - '0' + c;
            ch[i] = (char)('0' + bit % 10);
            c = bit / 10;
        }
        if (c == 1)
            return smallestWithNBit(ch.length + 1);
        return charArrToNum(flip(ch));
    }
    
    long getNextSmallerPolindrome(long num) {
        if (num == 1)
            return 0;
        char[] ch = numToCharArr(num);
        char[] flip = flip(ch);
        if (compare(ch, flip) > 0)
            return charArrToNum(flip);
        int c = 1;
        for (int i = ch.length % 2 == 0 ? (ch.length - 1) / 2 : ch.length / 2; i >= 0; i--)    {
            int bit = ch[i] - '0' - c;
            ch[i] = (char)('0' + (bit < 0 ? bit + 10 : bit));
            c = bit < 0 ? 1 : 0;
        }
        if (c == 1 || ch[0] == '0')
            return largestWithNBit(ch.length - 1);
        return charArrToNum(flip(ch));
    }
    
    char[] numToCharArr(long num)   {
        if (num == 0)
            return new char[] {'0'};
        long t = num;
        int n = 0;
        while (t != 0)  {
            t /= 10;
            n++;
        }
        char[] ch = new char[n];
        t = num;
        for (int i = n - 1; i >= 0; i--)    {
            ch[i] = (char)((int)(t % 10) + '0');
            t /= 10;
        }
        
        return ch;
    }
    
    char[] flip(char[] ch)   {
        int i = 0;
        int j = ch.length - 1;
        char[] ret = new char[ch.length];
        ret = ch.clone();
        while (i <= j)  {
            ret[j--] = ret[i++];
        }
        return ret;
    }
    
    int compare(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++)  {
            if (a[i] == b[i])
                continue;
            if (a[i] < b[i])
                return -1;
            return 1;
        }
        return 0;
    }
    
    long charArrToNum(char[] ch)    {
        long num = 0;
        for (char c : ch)   {
            num = num * 10 + (c - '0');
        }
        
        return num;
    }
    
    long smallestWithNBit(int n)    {
        long num = 1;
        for (int i = 0; i < n - 2; i++)
            num = num * 10;
        num = num * 10 + 1;
        return num;
    }
    
    long largestWithNBit(int n)    {
        long num = 9;
        for (int i = 0; i < n - 1; i++)
            num = num * 10 + 9;
        return num;
    }
}