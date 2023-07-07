class Solution {
    long[] factorial;
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        factorial = new long[11];
        factorial[0] = 1L;
        for (int i = 1; i <= 10; i++)
            factorial[i] = factorial[i - 1] * i;
        long ans = 10;
        for (int i = 2; i <= n; i++)    {
            ans += permutation(10, i) - permutation(9, i - 1);
        }
        
        return (int)ans;
    }
    
    long permutation(int n, int m)  {
        return factorial[n] / factorial[n - m];
    }
}