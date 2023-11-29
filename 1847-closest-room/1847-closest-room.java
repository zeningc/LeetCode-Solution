class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int[][] qs = new int[queries.length][];
        
        for (int i = 0; i < queries.length; i++)
            qs[i] = new int[] {queries[i][0], queries[i][1], i};
        
        Arrays.sort(qs, (a, b) -> b[1] - a[1]);
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        int p = 0;
        int[] ans = new int[queries.length];
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < qs.length; i++)    {
            int minSize = qs[i][1];
            while (p < rooms.length && rooms[p][1] >= minSize)  {
                treeSet.add(rooms[p][0]);
                p++;
            }
            int idx = qs[i][2];
            int preferId = qs[i][0];
            if (treeSet.contains(preferId))  {
                ans[idx] = preferId;
                continue;
            }
            Integer lo = treeSet.lower(preferId);
            Integer hi = treeSet.higher(preferId);
            if (lo == null || hi == null)   {
                
                ans[idx] = lo == null ? (hi == null ? -1 : hi) : lo;
                continue;
            }
            
            if (hi - preferId < preferId - lo)
                ans[idx] = hi;
            else
                ans[idx] = lo;
        }
        
        return ans;
    }
}