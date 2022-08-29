class Solution {
    public int removePalindromeSub(String s) {
        if (s.isEmpty())
            return 0;
        int lo = 0;
        int hi = s.length() - 1;
        while (lo < hi) {
            if (s.charAt(lo) == s.charAt(hi))   {
                lo++;
                hi--;
            }
            else
                return 2;
        }
        
        return 1;
    }
}
