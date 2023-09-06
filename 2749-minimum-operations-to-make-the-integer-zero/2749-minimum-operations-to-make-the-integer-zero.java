class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        int[] bits = new int[61];
        long x = num1;
        long y = num2;
        int i = 1;
        while (true)   {
            x -= y;
            if (i > x)
                return -1;
            int cnt = 0;
            for (int j = 0; j <= 60; j++)
                if ((x & (1L << j)) != 0)
                    cnt++;
            if (i >= cnt)
                return i;
            i++;
        }
    }
}