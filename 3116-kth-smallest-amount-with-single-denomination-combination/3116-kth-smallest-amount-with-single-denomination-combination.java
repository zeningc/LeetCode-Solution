class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int[] sign = new int[1 << n];
        for (int i = 1; i < (1 << n); i++)  {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0)
                    cnt++;
            }
            sign[i] = cnt % 2 == 0 ? -1 : 1;
        }
        
        long lo = 0;
        long hi = Long.MAX_VALUE / 2;
        while (lo <= hi)    {
            long mid = lo + (hi - lo) / 2;
            if(check(sign, coins, mid, k))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    boolean check(int[] signs, int[] coins, long mid, int k)  {
        int n = coins.length;
        long[] cnts = new long[n];
        long cnt = 0;
        for (int i = 1; i < n; i++)
            cnts[i] = mid / coins[i];
        for (int i = 0; i < (1 << n); i++) {
            int sign = signs[i];
            long curLCM = -1;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0)    {
                    if (curLCM == -1)
                        curLCM = coins[j];
                    else
                        curLCM = lcm(curLCM, coins[j]);
                }
            }
            cnt += (long)sign * (mid / curLCM);
        }
        return cnt >= k;
    }
    
    long lcm(long a, int b) {
        return a * b / gcd(a, b);
    }
    
    long gcd(long a, long b)  {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
}