class Solution {
    public boolean isReachable(int targetX, int targetY) {
        int gcd = calcGCD(targetX, targetY);
        while (gcd % 2 == 0)
            gcd /= 2;
        return gcd == 1;
    }
    
    int calcGCD(int a, int b)   {
        if (b == 0)
            return a;
        
        if (a < b)
            return calcGCD(b, a);
        
        return calcGCD(b, a % b);
    }
}