class Solution {
    long[] temp;
    public long kMirror(int k, int n) {
        temp = new long[64];
        int len = 1;
        long ans = 0;
        long power = 1;
        while (n > 0)   {
            long start = power;
            long end = power * 10 - 1;
            for (long i = start; i <= end; i++)  {
                long num = constructPalindrome(i, true, power * 10);
                if (check(num, k)){
                    n--;
                    ans += num;
                    if (n == 0)
                        break;
                }
            }
            if (n == 0)
                break;
            for (long i = start; i <= end; i++)  {
                long num = constructPalindrome(i, false, power * 10);
                if (check(num, k)){
                    n--;
                    ans += num;
                    if (n == 0)
                        break;
                }
            }
            
            len++;
            power *= 10;
        }
        
        return ans;
    }
    
    long constructPalindrome(long num, boolean odd, long power) {
        long rev = 0;
        long t = num;
        while (t != 0)  {
            rev = rev * 10 + t % 10;
            t /= 10;
        }
        
        if (odd)
            return (num / 10) * power + rev;
        return num * power + rev;
    }
    
    boolean check(long num, long k)    {
        int index = 0;
        long power = 1;
        long ret = 0;
        while (num != 0)    {
            temp[index++] = num % k;
            num /= k;
            power *= 10;
        }
        
        int i = 0;
        int j = index - 1;
        while (i < j)   {
            if (temp[i] == temp[j]) {
                i++;
                j--;
            }
            else    {
                return false;
            }
        }
        return true;
    }    
}
