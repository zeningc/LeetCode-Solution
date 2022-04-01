class Solution {
    int[] power;
    public int primePalindrome(int n) {
        int len = 0;
        int t = n;
        while (t != 0)  {
            t /= 10;
            len++;
        }
        len /= 2;
        if (len == 0)
            len = 1;
        power = new int[10];
        power[0] = 1;
        for (int i = 1; i <= 9; i++)
            power[i] = power[i - 1] * 10;
        
        while (true)    {
            int start = power[len - 1];
            int end = power[len];
            
            for (int i = start; i < end; i++)   {
                int num = constructPalindrome(i, true);
                if (num >= n && isPrime(num))
                    return num;
            }
            
            for (int i = start; i < end; i++)   {
                int num = constructPalindrome(i, false);
                if (num >= n && isPrime(num))
                    return num;
            }
            
            len++;
        }
        // return -1;
    }
    
    int constructPalindrome(int num, boolean odd)   {
        int rev = 0;
        int t = num;
        int cnt = 0;
        while (t != 0)  {
            rev = rev * 10 + t % 10;
            t /= 10;
            cnt++;
        }
        int ret;
        if (odd)
            ret = power[cnt] * (num / 10) + rev;
        else
            ret = power[cnt] * num + rev;
        return ret;
    }
    
    boolean isPrime(int k)   {
        if (k==1) return false;
        if (k%2==0) return k==2;
        for (int i=3; i*i<=k; i+=2)
        {
            if (k%i==0) return false;
        }
        return true;
    }
}