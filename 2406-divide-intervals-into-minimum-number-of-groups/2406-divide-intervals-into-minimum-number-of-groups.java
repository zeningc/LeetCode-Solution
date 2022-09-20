class Solution {
    public int minGroups(int[][] intervals) {
        int n = intervals.length;
        List<int[]> list = new LinkedList<>();
        for (int[] interval : intervals)    {
            list.add(new int[] {interval[0], 1});
            list.add(new int[] {interval[1], -1});
        }
        
        Collections.sort(list, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int bal = 0;
        for (int[] curr : list) {
            bal += curr[1];
            ans = Math.max(ans, bal);
        }
        
        return ans;
    }
}