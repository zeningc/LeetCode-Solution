class Solution {
    public String nearestPalindromic(String n) {
        long num = Long.valueOf(n);
        if (num <= 10)
            return String.valueOf(num - 1);
        List<Long> candidate = new ArrayList<>();
        boolean evenLen = n.length() % 2 == 0;
        String halfStr = n.substring(0, (n.length() + 1) / 2);
        long half = Long.valueOf(halfStr);
        candidate.add(allNine(n.length() - 1));
        candidate.add(flip(half - 1, evenLen));
        candidate.add(flip(half, evenLen));
        candidate.add(flip(half + 1, evenLen));
        candidate.add(powerOfTenPlusOne(n.length() + 1));
        long gap = Long.MAX_VALUE;
        String ans = "";
        for (long c : candidate)   {
            if (c == num)
                continue;
            if (gap > Math.abs(c - num))    {
                gap = Math.abs(c - num);
                ans = String.valueOf(c);
            }
        }
        
        return ans;
    }
    
    private long flip(long num, boolean flipLastDigit) {
        StringBuilder res = new StringBuilder(String.valueOf(num));
        long t = num;
        if (!flipLastDigit)
            t /= 10;
        while (t != 0) {
            res.append((char)('0' + t % 10));
            t /= 10;
        }
        return Long.valueOf(res.toString());
    }
    
    private long allNine(int len)  {
        long ret = 0;
        for (int i = 0; i < len; i++)
            ret = ret * 10 + 9;
        return ret;
    }
    
    private long powerOfTenPlusOne(int len)   {
        long ret = 1;
        for (int i = 0; i < len - 1; i++)
            ret = ret * 10;
        ret += 1;
        return ret;
    }
}