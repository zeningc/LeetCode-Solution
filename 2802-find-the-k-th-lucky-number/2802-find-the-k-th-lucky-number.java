class Solution {
    public String kthLuckyNumber(int k) {
        k++;
        boolean preZero = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--)   {
            if (!preZero && (k & (1 << i)) == 0)
                sb.append("4");
            else if (!preZero && (k & (1 << i)) != 0)
                sb.append("7");
            else if ((k & (1 << i)) != 0)
                preZero = false;
        }
        
        return sb.toString();
    }
}