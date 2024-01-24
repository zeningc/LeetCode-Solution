class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);
        int a = -1;
        int b = -1;
        int ans = 0;
        for (int[] interval : intervals)   {
            if (b < interval[0])    {
                a = interval[1] - 1;
                b = interval[1];
                ans += 2;
                continue;
            }
            if (a < interval[0])    {
                a = b;
                b = interval[1];
                ans++;
                continue;
            }
        }
        
        return ans;
    }
}