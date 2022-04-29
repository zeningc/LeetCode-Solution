class Solution {
    int[] batteries;
    int n;
    public long maxRunTime(int n, int[] batteries) {
        long left = 0;
        long right = (long)1e14;
        this.n = n;
        this.batteries = batteries;
        while (left <= right)   {
            long mid = left + (right - left) / 2;
            if (check(mid))
                left = mid + 1;
            else
                right = mid - 1;
        }
        
        return right;
    }
    
    boolean check(long mid) {
        long sum = 0;
        for (int bty : batteries)
            sum += Math.min(bty, mid);
        
        return sum >= mid * n;
    }
}
