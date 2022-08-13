class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; i++)
            presum[i + 1] = presum[i] + (s.charAt(i) == '*' ? 1 : 0);
        
        int[] next = new int[n];
        int[] prev = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|')
                prev[i] = i;
            else
                prev[i] = i == 0 ? -1 : prev[i - 1];
        }
        
        for (int i = n - 1; i > -1; i--)    {
            if (s.charAt(i) == '|')
                next[i] = i;
            else
                next[i] = i == n - 1 ? n : next[i + 1];
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++)    {
            int left = queries[i][0];
            int right = queries[i][1];
            int firstCandle = next[left];
            int lastCandle = prev[right];
            if (lastCandle > firstCandle)
                ans[i] = presum[lastCandle + 1] - presum[firstCandle];
        }
        
        return ans;
    }
}
