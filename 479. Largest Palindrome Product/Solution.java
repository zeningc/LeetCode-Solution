class Solution {
    public int largestPalindrome(int n) {
        if (n == 1)
            return 9;
        int[] time = new int[n + 1];
        time[0] = 1;
        for (int i = 1; i <= n; i++)
            time[i] = time[i - 1] * 10;
        long largest = (time[n] - 1) * (time[n] - 1);
        long smallest = time[n - 1] * time[n - 1];
        
        for (int i = time[n] - 1; i >= time[n - 1]; i--) {
            long num = (long)i * time[n] + (long)reverse(i);
            if (divisable(num, (long)time[n - 1], (long)time[n] - 1))
                return (int)(num % 1337);
        }
        
        for (int i = time[n - 1] - 1; i >= time[n - 2]; i--) {
            for (int j = 9; j >= 0; j--)    {
                long num = (long)i * time[n - 1] + (long)j * time[n - 2] + (long)reverse(i);
                if (num > largest)
                    continue;
                if (num < smallest)
                    break;
                if (divisable(num, (long)time[n - 1], (long)time[n] - 1))
                    return (int)(num % 1337);
            }
        }
        
        return -1;
    }
    
    int reverse(int num)    {
        int ret = 0;
        while (num != 0)    {
            ret = ret * 10 + num % 10;
            num /= 10;
        }
        return ret;
    }
    
    boolean divisable(long num, long sm, long max) {
        long start = (long)Math.sqrt(num);
        for (long i = start; i <= max; i++)   {
            if (num % i == 0 && num / i <= max)
                return true;
        }
        return false;
    }
}

