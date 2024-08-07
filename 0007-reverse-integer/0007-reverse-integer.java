class Solution {
    public int reverse(int x) {
        if (x == 0)
            return 0;
        boolean positive = x > 0;
        x = Math.abs(x);
        int rev = 0;
        while (x != 0)    {
            int r = x % 10;
            if (rev > Integer.MAX_VALUE / 10 || rev * 10 > Integer.MAX_VALUE - r)
                return 0;
            rev = rev * 10 + r;
            x /= 10;
        }
        if (!positive)
            rev *= -1;
        return rev;
    }
}