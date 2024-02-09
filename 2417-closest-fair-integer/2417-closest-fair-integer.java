class Solution {
    public int closestFair(int n) {
        int cnt = 0;
        int ret = helper(n, cnt);
        if (ret != -1)
            return ret;
        while (n >= 0)  {
            ret = helper(n + 1, cnt);
            if (ret != -1)
                return ret;
            ret = helper(n + 2, cnt);
            if (ret != -1)
                return ret;
            n /= 10;
            cnt++;
        }
        
        return -1;
    }
    
    int helper(int n, int cnt)    {
        int odd = 0;
        int even = 0;
        int m = n;
        while (m != 0)  {
            if (m % 2 != 0)
                odd++;
            else
                even++;
            m /= 10;
        }
        
        int diff = odd - even;
        int sum = cnt;
        if ((diff + sum) % 2 != 0)
            return -1;
        int a = (sum + diff) / 2;
        int b = (sum - diff) / 2;
        if (a < 0 || b < 0)
            return -1;
        for (int i = 0; i < a; i++)
            n = n * 10;
        for (int i = 0; i < b; i++)
            n = n * 10 + 1;
        return n;
    }
}