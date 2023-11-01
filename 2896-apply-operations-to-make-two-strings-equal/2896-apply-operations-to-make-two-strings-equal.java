class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            if (s1.charAt(i) != s2.charAt(i))
                diff.add(i);
        if (diff.size() % 2 != 0)
            return -1;
        if (diff.isEmpty())
            return 0;
        
        double[] dp = new double[diff.size() + 1];
        Arrays.fill(dp, Double.MAX_VALUE);
        int m = dp.length;
        dp[m - 1] = 0;
        dp[m - 2] = (double)x / 2;
        for (int i = m - 3; i >= 0; i--)
            dp[i] = Math.min(dp[i + 1] + (double)x / 2, dp[i + 2] + diff.get(i + 1) - diff.get(i));
        return (int)dp[0];
    }
}