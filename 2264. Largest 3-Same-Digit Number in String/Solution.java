class Solution {
    public String largestGoodInteger(String num) {
        boolean[] exists = new boolean[10];
        String ans = "";
        for (int i = 0; i < num.length() - 2; i++)  {
            if (num.charAt(i) == num.charAt(i + 1) && num.charAt(i) == num.charAt(i + 2))   {
                exists[num.charAt(i) - '0'] = true;
            }
        }
        
        for (int i = 9; i >= 0; i--)    {
            if (exists[i])  {
                for (int j = 0; j < 3;j++)
                    ans += (i + "");
                break;
            }
        }
        
        return ans;
    }
}
