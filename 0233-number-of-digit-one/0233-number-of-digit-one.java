class Solution {
    public int countDigitOne(int n) {
        int power = 1;
        int next = 0;
        int pre = n;
        int ans = 0;
        while (pre != 0)  {
            int r = pre % 10;
            pre /= 10;
            ans += pre * power;
            if (r > 1)
                ans += power;
            else if (r == 1)
                ans += next + 1;
            next = r * power + next;
            power *= 10;
        }
        
        return ans;
    }
}