class Solution {
    public int[] amountPainted(int[][] paint) {
        int n = paint.length;
        int[] ans = new int[n];
        TreeMap<Integer, List<int[]>> m = new TreeMap<>();
        for (int i = 0; i < paint.length; i++)  {
            int lo = paint[i][0];
            int hi = paint[i][1];
            m.computeIfAbsent(lo, x -> new ArrayList<>()).add(new int[] {i, 1});
            m.computeIfAbsent(hi, x -> new ArrayList<>()).add(new int[] {i, -1});
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (Map.Entry<Integer, List<int[]>> e : m.entrySet())  {
            List<int[]> list = e.getValue();
            int pos = e.getKey();
            for (int[] l : list)    {
                if (l[1] == 1)
                    set.add(l[0]);
                else
                    set.remove(l[0]);
            }
            
            if (!set.isEmpty())   {
                ans[set.first()] += m.higherKey(pos) - pos;
            }
        }
        
        return ans;
    }
}