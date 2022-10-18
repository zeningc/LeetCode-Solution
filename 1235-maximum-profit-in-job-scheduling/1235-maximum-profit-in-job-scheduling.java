class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        
        int[] dp = new int[n];
        dp[0] = jobs[0][2];
        for (int i = 1; i < n; i++)    {
            int lo = 0;
            int hi = i - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (jobs[mid][1] > jobs[i][0])  {
                    hi = mid - 1;
                }
                else    {
                    lo = mid + 1;
                }
            }
            int j = hi;
            dp[i] = Math.max((j >= 0 ? dp[j] : 0) + jobs[i][2], dp[i - 1]);
        }
        
        return dp[n - 1];
    }
}
