class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeMap<Integer, List<int[]>> m = new TreeMap<>();
        for (int[] building : buildings)    {
            int lo = building[0];
            int hi = building[1];
            int h = building[2];
            m.computeIfAbsent(lo, x -> new ArrayList<>()).add(new int[] {h, 1});
            m.computeIfAbsent(hi, x -> new ArrayList<>()).add(new int[] {h, -1});
        }
        List<List<Integer>> ans = new ArrayList<>();
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        int pre = -1;
        for (Map.Entry<Integer, List<int[]>> e : m.entrySet())  {
            int pos = e.getKey();
            List<int[]> list = e.getValue();
            for (int[] l : list)  {
                int h = l[0];
                int op = l[1];
                if (op == 1)    {
                    freq.put(h, freq.getOrDefault(h, 0) + 1);
                }
                else    {
                    freq.put(h, freq.getOrDefault(h, 0) - 1);
                }
                
                if (freq.get(h) == 0)
                    freq.remove(h);
            }
            int cur = freq.isEmpty() ? 0 : freq.lastKey();
            if (cur != pre)
                ans.add(List.of(pos, cur));
            pre = cur;
        }
        
        return ans;
    }
}