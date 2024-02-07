class Solution {
    public long findMaximumNumber(long k, int x) {
        long lo = 1;
        long hi = Long.MAX_VALUE;
        while (lo <= hi)    {
            long mid = lo + (hi - lo) / 2;
            if (!check(mid, k, x))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return hi;
    }
    
    boolean check(long n, long k, int x)    {
        n++;
        long sum = 0;
        long power = 1;
        for (int i = 1; i <= 62; i++)    {
            power *= 2;
            if (i % x != 0)
                continue;
            sum += (n / power) * power / 2 + Math.max(0, n % power - power / 2);
            if (sum > k)
                return false;
        }
        return true;
    }
}