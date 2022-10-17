class Solution {
    public int scoreOfParentheses(String s) {
        int bal = 0;
        int ans = 0;
        for (int i = 0; i < s.length(); i++)  {
            char c = s.charAt(i);
            if (c == '(')   {
                bal++;
            }
            else    {
                bal--;
                if (s.charAt(i - 1) == '(') {
                    ans += 1 << bal;
                }
            }
        }
        
        return ans;
    }
}
