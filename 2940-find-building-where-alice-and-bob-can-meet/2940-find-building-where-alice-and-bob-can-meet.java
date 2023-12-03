import java.util.NavigableSet;
import java.util.NavigableMap;
class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        TreeMap<Integer, TreeSet<int[]>> qs = new TreeMap<>();
        int[][] qss = new int[queries.length][3];
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < queries.length; i++)    {
            qss[i] = new int[] {queries[i][0], queries[i][1], i};
        }
            
        Arrays.sort(qss, (a, b) -> Math.max(a[0], a[1]) - Math.max(b[0], b[1]));
        int p = 0;
        
        for (int i = 0; i < heights.length; i++)    {
            while (p < qss.length && qss[p][0] <= i && qss[p][1] <= i)    {
                qs.computeIfAbsent(heights[qss[p][0]], x -> new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0])).add(new int[] {heights[qss[p][1]], qss[p][2]});
                p++;
            }
            if (qs.lowerKey(heights[i]) != null)    {
                NavigableMap<Integer, TreeSet<int[]>> subMap = qs.subMap(qs.firstKey(), true, qs.lowerKey(heights[i]), true);
                Iterator<Map.Entry<Integer, TreeSet<int[]>>> iterator = subMap.entrySet().iterator();
                while (iterator.hasNext())  {
                    TreeSet<int[]> querySet = iterator.next().getValue();
                    if (querySet.lower(new int[] {heights[i], -1}) != null) {
                        NavigableSet<int[]> subSet = querySet.subSet(querySet.first(), true, querySet.lower(new int[] {heights[i], -1}), true);
                        Iterator<int[]> setIterator = subSet.iterator();
                        while (setIterator.hasNext())   {
                            int[] cur = setIterator.next();
                            ans[cur[1]] = i;
                            setIterator.remove();
                        }
                    }
                    if (querySet.isEmpty())
                        iterator.remove();
                }
            }
        }
        
        for (int i = 0; i < qss.length; i++)    {
            if (qss[i][0] == qss[i][1])
                ans[qss[i][2]] = qss[i][0];
            else if (qss[i][0] < qss[i][1] && heights[qss[i][0]] < heights[qss[i][1]])
                ans[qss[i][2]] = qss[i][1];
            else if (qss[i][1] < qss[i][0] && heights[qss[i][1]] < heights[qss[i][0]])
                ans[qss[i][2]] = qss[i][0];
        }
        
        return ans;
    }
}