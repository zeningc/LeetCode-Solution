class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        double lo = 0;
        double hi = stations[n - 1];
        
        while (lo + 1e-6 <= hi) {
            double mid = lo + (hi - lo) / 2;
            if (check(stations, k, mid))
                hi = mid - 1e-6;
            else
                lo = mid + 1e-6;
        }
        
        return lo;
    }
    
    boolean check(int[] stations, int k, double dist) {
        for (int i = 1; i < stations.length; i++)   {
            double gap = (double)stations[i] - stations[i - 1];
            if (gap <= dist)
                continue;
            int time = (int)Math.floor(gap / dist);
            if (k < time)
                return false;
            k -= time;
        }
        
        return true;
    }
}