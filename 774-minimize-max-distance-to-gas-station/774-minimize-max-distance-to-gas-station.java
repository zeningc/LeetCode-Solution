class Solution {
    public double minmaxGasDist(int[] stations, int k) {
        double left = 0;
        int n = stations.length;
        double right = -1;
        for (int i = 1; i < n; i++)
            right = Math.max(right, stations[i] - stations[i - 1]);
        
        if (right == 0)
            return .0;
        
        while (left + 1e-6 <= right)   {
            double mid = left + (right - left) / 2;
            if (check(stations, k, mid))
                right = mid - 1e-6;
            else
                left = mid + 1e-6;
        }
        
        return left;
    }
    
    boolean check(int[] stations, int k, double dist)   {
        if (dist == 0)
            return false;
        int n = stations.length;
        for (int i = 1; i < n; i++) {
            double gap = stations[i] - stations[i - 1];
            if (gap <= dist)
                continue;
            if (k < 0)
                return false;
            double cnt = gap / dist;
            k -= (int)(Math.ceil(cnt)) - 1;
            if (k < 0)
                return false;
        }
        return true;
    }
}

// x x x o o o 