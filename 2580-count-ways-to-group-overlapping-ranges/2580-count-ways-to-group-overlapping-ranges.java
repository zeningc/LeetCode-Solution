class Solution {
    public int countWays(int[][] ranges) {
        int lo = -1;
        int hi = -1;
        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);
        int grp = 0;
        for (int[] range : ranges)  {
            if (hi < range[0])  {
                grp++;
                lo = range[0];
                hi = range[1];
                continue;
            }
            
            hi = Math.max(hi, range[1]);
        }
        
        
        int mod = (int)1e9 + 7;
        long ans = 1;
        for (int i = 0; i < grp; i++)
            ans = (ans * 2) % mod;
        return (int)(ans % mod);
    }
}