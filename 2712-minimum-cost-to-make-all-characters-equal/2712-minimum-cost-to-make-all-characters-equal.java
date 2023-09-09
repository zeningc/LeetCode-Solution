class Solution {
    public long minimumCost(String s) {
        long res = 0;
        for (int i = 1, n = s.length(); i < n; ++i)
            if (s.charAt(i) != s.charAt(i - 1))
                res += Math.min(i, n - i);
        return res;
    }
}
 
