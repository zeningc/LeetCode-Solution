class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        List<int[]> jobs = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
        Collections.sort(jobs, (a, b) -> a[1] - b[1]);
        dp.put(-1, 0);
        
        for (int[] job : jobs)  {
            int start = job[0];
            int end = job[1];
            int earn = job[2];
            dp.put(end, Math.max(dp.floorEntry(end).getValue(), dp.floorEntry(start).getValue() + earn));
        }
        
        return dp.lastEntry().getValue();
    }
}

// dp[i] = max(dp[i], dp[startTime] + profit)