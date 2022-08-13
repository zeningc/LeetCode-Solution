class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        int aLen = 0;
        int bLen = 0;
        while (a != b)  {
            if (a == b)
                break;
            if (a > b && aLen < 2 || a < b && bLen >= 2)  {
                sb.append('a');
                bLen = 0;
                aLen++;
                a--;
            }
            else   {
                sb.append('b');
                aLen = 0;
                bLen++;
                b--;
            }
        }
        
        while (a == b && a != 0)    {
            if (aLen > 0)  {
                sb.append('b');
                sb.append('a');
            }
            else    {
                sb.append('a');
                sb.append('b');
            }
            a--;
            b--;
        }
        
        return sb.toString();
    }
}
