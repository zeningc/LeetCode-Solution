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
            int mid = 3 * ((int)Math.pow(2, i) - 1) / 2;
            if (k <= mid)   {
                sb.append("4");
                k -= Math.pow(2, i - 1);
            }
            else    {
                sb.append("7");
                k -= Math.pow(2, i);
            }
            
        }
        
        return sb.toString();
    }
}

/*
4 
7
44
47
74
77
444
447
474
477
744
747
774
777

2 ^ n - 2
2 ^ (n + 1) - 2

2^n-1+2^(n+1)-2

3 * 2^n - 3 = 3(2 ^ n - 1)

*/

