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
        int ans = Math.max(a, b);
        while (ans % a != 0 || ans % b != 0)
            ans++;
        return ans;
    }
}
