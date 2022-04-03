class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> loseMap = new HashMap<>();
        TreeSet<Integer> player = new TreeSet<>();
        for (int[] match : matches) {
            int w = match[0];
            int l = match[1];
            player.add(w);
            player.add(l);
            loseMap.put(l, loseMap.getOrDefault(l, 0) + 1);
        }
        List<List<Integer>> ans = new LinkedList<>();
        ans.add(new LinkedList<>());
        ans.add(new LinkedList<>());
        for (int p : player)    {
            if (!loseMap.containsKey(p) || loseMap.get(p) == 0)    {
                ans.get(0).add(p);
            }
            else if (loseMap.get(p) == 1)   {
                ans.get(1).add(p);
            }
        }
        return ans;
    }
}
