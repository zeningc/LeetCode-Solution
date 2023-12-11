class Solution {
    public String kthLuckyNumber(int k) {
        StringBuilder sb = new StringBuilder();
        int len = -1;
        for (int i = 31; i >= 0; i--)   {
            if (((k + 2) & (1 << i)) != 0)  {
                if (k + 2 > (1 << i))
                    len = i;
                else
                    len = i - 1;
                break;
            }
        }
        for (int i = len; i >= 1; i--)    {
            int mid = 3 * ((1 << i) - 1) / 2;
            if (k <= mid)   {
                sb.append("4");
                k -= (1 << (i - 1));
            }
            else    {
                sb.append("7");
                k -= (1 << i);
            }
            
        }
        
        return sb.toString();
    }
}

