class Solution {
    public String minRemoveToMakeValid(String s) {
        int left = 0;
        int right = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++)  {
            char c = str[i];
            if (c == '(')   {
                left++;
            }
            else if (c == ')')  {
                if (left > 0)
                    left--;
                else
                    str[i] = '#';
            }
        }
        
        for (int i = str.length - 1; i >= 0; i--)
            if (left > 0 && str[i] == '(')  {
                str[i] = '#';
                left--;
            }
        
        StringBuilder sb = new StringBuilder();
        for (char c : str)
            if (c != '#')
                sb.append(c);
        
        return sb.toString();
    }
}
