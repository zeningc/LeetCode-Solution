class Solution {
    public long maxTaxiEarnings(int n, int[][] rides) {
        TreeMap<Integer, Long> dp = new TreeMap<>();
        int m = rides.length;
        for (int i = 0; i < m; i++) {
            rides[i][2] = rides[i][1] - rides[i][0] + rides[i][2];
        }
        dp.put(0, 0L);
        Arrays.sort(rides, (a, b) -> a[1] - b[1]);
        for (int i = 0; i < m; i++) {
            int start = rides[i][0];
            int end = rides[i][1];
            int earn = rides[i][2];
            dp.put(end, Math.max(dp.floorEntry(end).getValue(), dp.floorEntry(start).getValue() + earn));
        }
        
        return dp.isEmpty() ? 0 : dp.lastEntry().getValue();
    }
}