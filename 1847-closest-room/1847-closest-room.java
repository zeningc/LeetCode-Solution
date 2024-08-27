class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        int m = queries.length;
        List<Integer> list = new ArrayList<>();
        List<Integer> queryIdx = new ArrayList<>();
        for (int i = 0; i < m; i++)
            queryIdx.add(i);
        Collections.sort(queryIdx, (a, b) -> queries[b][1] - queries[a][1]);
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        int j = 0;
        int[] ans = new int[m];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : queryIdx)  {
            while (j < rooms.length && rooms[j][1] >= queries[i][1])    {
                set.add(rooms[j][0]);
                j++;
            }
            
            if (set.isEmpty())  {
                ans[i] = -1;
                continue;
            }
            
            Integer floor = set.floor(queries[i][0]);
            Integer ceiling = set.ceiling(queries[i][0]);
            if (floor != null && ceiling != null)  {
                if (Math.abs(floor - queries[i][0]) <= Math.abs(ceiling - queries[i][0]))
                    ans[i] = floor;
                else
                    ans[i] = ceiling;
                continue;
            }
            
            if (floor == null)
                ans[i] = ceiling;
            else if (ceiling == null)
                ans[i] = floor;
        }
        
        return ans;
    }
}