class Solution {
    public int mirrorReflection(int p, int q) {
        int lcm = getLcm(p, q);
        int n = lcm / p;
        int m = lcm / q;
        
        if (n % 2 == 0 && m % 2 == 0)
            return 3;
        if (n % 2 == 0 && m % 2 != 0)
            return 0;
        if (m % 2 == 0)
            return 2;
        return 1;
    }
    
    int getLcm(int a, int b)   {
        return a * b / getGCD(a, b);
    }
    
    int getGCD(int n1, int n2)  {
        if (n2 > n1)
            return getGCD(n2, n1);
        a
        if (n2 == 0)
            return n1;
        
        return getGCD(n2, n1 % n2);
    }
}
