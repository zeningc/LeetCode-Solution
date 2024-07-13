class Solution {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int last = 0;
        for (int[] m : meetings)    {
            int start = m[0];
            int end = m[1];
            if (last < start)
                ans += start - last - 1;
            last = Math.max(last, end);
        }
        if (last < days)
            ans += days - last;
        return ans;
    }
}