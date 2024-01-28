class Solution {
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int grp = 0;
        int far = Integer.MIN_VALUE;
        for (int[] range : ranges)  {
            if (range[0] > far) {
                grp++;
                far = range[1];
                continue;
            }
            
            far = Math.max(far, range[1]);
        }
        
        long ans = 1;
        for (int i = 0; i < grp; i++)
            ans = (ans * 2) % 1000000007;
        return (int)ans;
    }
}