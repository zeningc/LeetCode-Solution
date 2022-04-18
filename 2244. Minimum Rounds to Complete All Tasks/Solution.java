class Solution {
    public int minimumRounds(int[] tasks) {
        int n = tasks.length;
        int[] dp = new int[Math.max(5, n + 1)];
        dp[0] = 0;
        dp[1] = -1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        for (int i = 5; i <= n; i++)    {
            dp[i] = Math.min(dp[i - 2], dp[i - 3]) + 1;
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int task : tasks)  {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        int ans = 0;
        for (int level : freq.keySet()) {
            int f = freq.get(level);
            if (f == 1)
                return -1;
            ans += dp[f];
        }
        
        return ans;
    }
}

