class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int[] factorial = new int[10];
        boolean[] used = new boolean[10];
        factorial[0] = 1;
        for (int i = 1; i <= 9; i++)
            factorial[i] = factorial[i - 1] * i;
        k--;
        for (int i = 0; i < n ; i++)    {
            int left = n - i - 1;
            int cnt = k / factorial[left];
            for (int j = 1; j <= 9; j++)    {
                if (used[j])
                    continue;
                if (cnt == 0)   {
                    used[j] = true;
                    sb.append((char)(j + '0'));
                    break;
                }
                cnt--;
            }
            
            k %= factorial[left];
        }
        
        return sb.toString();
    }
}