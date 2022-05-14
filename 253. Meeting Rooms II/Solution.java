class Solution {
    public int minMeetingRooms(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int[] interval : intervals)    {
            int start = interval[0];
            int end = interval[1];
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }
        int ans = 0;
        int cnt = 0;
        for (int key : map.keySet())    {
            cnt += map.get(key);
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
