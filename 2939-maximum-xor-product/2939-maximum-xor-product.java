class Solution {
    public int maximumXorProduct(long a, long b, int n) {
        if (a < b)
            return maximumXorProduct(b, a, n);
        long num1 = a;
        long num2 = b;
        long mod = 1000000007;
        List<Integer> unselected = new LinkedList<>();
        for (int i = n - 1; i >= 0; i--) {
            long bit = 1L << i;
            int b1 = (a & bit) == 0 ? 0 : 1;
            int b2 = (b & bit) == 0 ? 0 : 1;
            
            if (b1 == b2)   {
                num1 |= bit;
                num2 |= bit;
                continue;
            }
            
            if (b1 != 0)
                num1 -= (1L << i);

            if (b2 != 0)
                num2 -= (1L << i);

            unselected.add(i);
        }
        
        if (num1 < num2)    {
            long t = num1;
            num1 = num2;
            num2 = t;
        }
        
        for (int idx : unselected)  {
            if (num1 == num2)   {
                num1 |= (1L << idx);
                continue;
            }
            num2 |= (1L << idx);
        }
        
        
        
        return (int)(((num1 % mod) * (num2 % mod)) % mod);
    }
}


// a == 0 b == 0 -> c == 1
// a == 1 b == 1 -> c == 1
// a == 0 b == 1 ->
//     a > b
//     c == 1 -> (a + x) * (b - x) -> a * b + (b - a) * x - x ^ 2
//     c == 0 -> (a) * (b) -> a * b
    
// a == 1 b == 0 ->
//     a > b
//     c == 1 (a - x) * (b + x) = a * b + (a - b) * x - x ^ 2
//     c 