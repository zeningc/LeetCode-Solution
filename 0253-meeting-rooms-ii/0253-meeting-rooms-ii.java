class Solution {
    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (int[] interval : intervals)    {
            int lo = interval[0];
            int hi = interval[1];
            m.put(lo, m.getOrDefault(lo, 0) + 1);
            m.put(hi, m.getOrDefault(hi, 0) - 1);
        }
        int cnt = 0;
        int ans = 0;
        for (Map.Entry<Integer, Integer> e : m.entrySet())  {
            cnt += e.getValue();
            ans = Math.max(ans, cnt);
        }
        
        return ans;
    }
}