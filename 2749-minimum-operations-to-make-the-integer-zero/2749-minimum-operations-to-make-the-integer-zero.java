class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        long x = num1;
        long y = num2;
        for (int i = 1; i <= 60; i++)   {
            x -= y;
            if (x < i)
                break;
            int cnt = 0;
            for (int j = 0; j <= 60; j++)
                if ((x & (1L << j)) != 0)
                    cnt++;
            if (i >= cnt)
                return i;
        }
        return -1;
    }
}