class Solution {
    public int digitsCount(int d, int low, int high) {
        return countDigit(d, high) - countDigit(d, low - 1);
    }
    
    int countDigit(int d, int n)    {
        int power = 1;
        int pre = n;
        int next = 0;
        int ans = 0;
        while ((d == 0 && pre / 10 != 0 || d != 0 && pre != 0))    {
            int r = pre % 10;
            pre /= 10;
            ans += (d == 0 ? pre - 1 : pre) * power;
            
            if (r > d)
                ans += power;
            else if (r == d)
                ans += next + 1;
            
            next = r * power + next;
            power *= 10;
        }
        
        return ans + (d == 0 ? 1 : 0);
    }
}