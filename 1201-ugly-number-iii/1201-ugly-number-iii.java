class Solution {
    long n, a, b, c;
    long lcm1, lcm2, lcm3, lcm4;
    public int nthUglyNumber(int n, int a, int b, int c) {
        this.n = n;
        this.a = a;
        this.b = b;
        this.c = c;
        lcm1 = getLCM(a, b);
        lcm2 = getLCM(b, c);
        lcm3 = getLCM(a, c);
        lcm4 = getLCM(lcm1, c);
        long left = 1;
        long right = 2 * (int)1e9;
        while (left <= right)   {
            long mid = left + (right - left) / 2;
            if (check(mid)) 
                right = mid - 1;
            else
                left = mid + 1;
        }
        return (int)left;
    }
    
    boolean check(long num) {
        long cnt1 = num / a;
        long cnt2 = num / b;
        long cnt3 = num / c;
        long cnt4 = num / lcm1;
        long cnt5 = num / lcm2;
        long cnt6 = num / lcm3;
        long cnt7 = num / lcm4;
        return cnt1 + cnt2 + cnt3 - cnt4 - cnt5 - cnt6 + cnt7 >= n;
    }
    
    long getLCM(long a, long b) {
        return a * b / getGCD(a, b);
    }
    
    long getGCD(long a,  long b)    {
        if (a < b)
            return getGCD(b, a);
        a = a % b;
        if (a == 0)
            return b;
        return getGCD(b, a);
    }
}

// ugly number guess: x
// if there is k ugly number lower than / eequals to x: x smaller
// else x larger


// x x x | o o o
// 2 4 6