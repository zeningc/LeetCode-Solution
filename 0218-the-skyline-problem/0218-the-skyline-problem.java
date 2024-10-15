class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for (int[] building : buildings)    {
            map.computeIfAbsent(building[0], x -> new ArrayList<>()).add(new int[] {building[2], 1});
            map.computeIfAbsent(building[1], x -> new ArrayList<>()).add(new int[] {building[2], -1});
        }
        
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        
        List<List<Integer>> ans = new ArrayList<>();
        int pre = -1;
        for (Map.Entry<Integer, List<int[]>> entry : map.entrySet())    {
            List<int[]> list = entry.getValue();
            
            for (int[] l : list)    {
                if (l[1] == 1)  {
                    freq.put(l[0], freq.getOrDefault(l[0], 0) + 1);
                    continue;
                }
                
                freq.put(l[0], freq.getOrDefault(l[0], 0) - 1);
                if (freq.get(l[0]) == 0)
                    freq.remove(l[0]);
            }
            int cur = freq.isEmpty() ? 0 : freq.lastKey();
            if (cur != pre)
                ans.add(List.of(entry.getKey(), cur));
            pre = cur;
        }
        
        return ans;
    }
}