class Solution {
    public String largestPalindromic(String num) {
        int[] cnt = new int[10];
        for (char c : num.toCharArray())    {
            cnt[c - '0']++;
        }
        StringBuilder sb = new StringBuilder();
        int last = -1;
        boolean flag = false;
        for (int i = 9; i >= 0; i--)    {
            if (i == 0 && !flag)    {
                return last != -1 ? String.valueOf(last) : "0";
            }
            int repeat = cnt[i] / 2;
            if (repeat > 0)
                flag = true;
            cnt[i] %= 2;
            if (cnt[i] > 0 && last == -1)
                last = i;
            for (int j = 0; j < repeat; j++)
                sb.append((char)(i + '0'));
        }
        String front = sb.toString();
        String end = sb.reverse().toString();
        
        if (last != -1)
            front += String.valueOf(last);
        
        return front + end;
    }
}