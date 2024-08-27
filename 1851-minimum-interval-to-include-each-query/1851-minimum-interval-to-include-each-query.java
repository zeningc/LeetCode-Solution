class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < queries.length; i++)
            treeMap.computeIfAbsent(queries[i], x -> new ArrayList<>()).add(i);
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        for (int[] interval : intervals)    {
            int size = interval[1] - interval[0] + 1;
            while (next(treeMap.ceilingKey(interval[0]), interval[1])) {
                Integer k = treeMap.ceilingKey(interval[0]);
                List<Integer> idxs = treeMap.get(k);
                for (int i : idxs)
                    ans[i] = size;
                treeMap.remove(k);
            }
        }
        
        return ans;
    }
    
    boolean next(Integer a, int b)  {
        if (a == null)
            return false;
        return a <= b;
    }
}