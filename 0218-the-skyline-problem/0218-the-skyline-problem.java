class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer, List<int[]>> points = new TreeMap<>();
        for (int[] building : buildings)    {
            points.computeIfAbsent(building[0], x -> new ArrayList<>()).add(new int[] {building[2], 1});
            points.computeIfAbsent(building[1], x -> new ArrayList<>()).add(new int[] {building[2], -1});
        }
        
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<int[]>> entry : points.entrySet())   {
            int pos = entry.getKey();
            List<int[]> list = entry.getValue();
            for (int[] cur : list)  {
                freq.put(cur[0], freq.getOrDefault(cur[0], 0) + cur[1]);
                if (freq.get(cur[0]) == 0)
                    freq.remove(cur[0]);
            }
            int cur = freq.isEmpty() ? 0 : freq.lastKey();
            if (ans.isEmpty() || cur != ans.get(ans.size() - 1).get(1))
                ans.add(List.of(pos, cur));
        }
        
        return ans;
    }
}