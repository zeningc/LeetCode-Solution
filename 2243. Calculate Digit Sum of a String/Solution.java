class Solution {
    public String digitSum(String s, int k) {
        StringBuilder next = new StringBuilder();
        while (s.length() > k)    {
            for (int i = 0; i < s.length(); i += k)   {
                next.append(sumStr(s, i, Math.min(s.length() - 1, i + k - 1)));
            }
            s = next.toString();
            next = new StringBuilder();
        }
        
        return s;
    }
    
    String sumStr(String s, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++)  {
            char c = s.charAt(i);
            sum += (c - '0');
        }
        return String.valueOf(sum);
    }
}
