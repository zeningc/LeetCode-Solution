class Solution {
    public String getPermutation(int n, int k) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++)
            dp[i] = dp[i - 1] * i;
        int[] candidate = new int[10];
        for (int i = 1; i <= 9; i++)
            candidate[i] = i;
        
        int[] nums = new int[n];
        k--;
        for (int i = 0; i < n; i++) {
            int left = n - i - 1;
            int idx = k / dp[left];
            int x = 1;
            while (candidate[x] == -1)
                    x++;
            for (int j = 0; j < idx; j++)   {
                while (candidate[x] == -1)
                    x++;
                x++;
            }
            while (candidate[x] == -1)
                x++;
            nums[i] = x;
            candidate[x] = -1;
            k -= idx * dp[left];
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append(nums[i]);
        
        return sb.toString();
    }
}
