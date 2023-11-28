class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int n = nums.size();
        int[] p = new int[n];
        TreeSet<Pair<Integer, Integer>> treeSet = new TreeSet<>((a, b) -> a.getValue().intValue() != b.getValue().intValue() ? a.getValue() - b.getValue() : a.getKey() - b.getKey());
        
        for (int i = 0; i < n; i++)
            treeSet.add(new Pair<Integer, Integer> (i, nums.get(i).get(0)));
        
        int[] ans = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int range = Integer.MAX_VALUE;
        while (true)    {
            Pair<Integer, Integer> first = treeSet.first();
            Pair<Integer, Integer> last = treeSet.last();
            if (range > last.getValue() - first.getValue()) {
                range = last.getValue() - first.getValue();
                ans = new int[] {first.getValue(), last.getValue()};
            }
            int idx = first.getKey();
            
            p[idx]++;
            if (p[idx] == nums.get(idx).size())
                break;
            treeSet.remove(first);
            treeSet.add(new Pair<Integer, Integer> (idx, nums.get(idx).get(p[idx])));
        }
        
        
        return ans;
    }
}
