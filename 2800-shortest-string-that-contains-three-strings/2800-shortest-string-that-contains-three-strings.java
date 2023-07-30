class Solution {
    public String minimumString(String a, String b, String c) {
        String ans = null;
        ans = getMax(ans, concat(concat(a, b), c));
        ans = getMax(ans, concat(concat(a, c), b));
        ans = getMax(ans, concat(concat(b, a), c));
        ans = getMax(ans, concat(concat(b, c), a));
        ans = getMax(ans, concat(concat(c, a), b));
        ans = getMax(ans, concat(concat(c, b), a));
        return ans;
    }
    
    String getMax(String a, String b)   {
        if (a == null)
            return b;
        if (b == null)
            return a;
        if (a.length() > b.length())
            return b;
        if (b.length() > a.length())
            return a;
        
        for (int i = 0; i < a.length(); i++)    {
            if (a.charAt(i) < b.charAt(i))
                return a;
            if (b.charAt(i) < a.charAt(i))
                return b;
        }
        
        return a;
    }
    
    String concat(String a, String b) {
        if (a.indexOf(b) != -1)
            return a;
        int idx = 0;
        for (int i = 0; i < a.length(); i++)    {
            boolean notMatch = false;
            int k = i;
            int j = 0;
            for (; k < a.length() && j < b.length(); j++)    {
                if (a.charAt(k) != b.charAt(j)) {
                    notMatch = true;
                    break;
                }
                k++;
            }
            if (!notMatch)  {
                idx = Math.max(idx, j);
            }
        }
        return a + b.substring(idx);
    }
}