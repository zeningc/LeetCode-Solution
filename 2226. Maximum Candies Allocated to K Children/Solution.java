class Solution {
    public int maximumCandies(int[] candies, long k) {
        long ttl = 0;
        for (int candy : candies)
            ttl += candy;
        long left = 0;
        long right = (long)1e12;//(ttl + 1) / k;
        while (left <= right)   {
            long mid = left + (right - left) / 2;
            if (!check(mid, candies, k))
                right = mid - 1;
            else 
                left = mid + 1;
        }
        
        return (int)(left - 1);
    }
    
    boolean check(long each, int[] candies, long k) {
        if (each == 0)
            return true;
        for (int i = 0; i < candies.length && k > 0; i++)    {
            k -= Math.min(k, (long)candies[i] / each);
        }
        if (k <= 0)
            return true;
        return false;
    }
}
