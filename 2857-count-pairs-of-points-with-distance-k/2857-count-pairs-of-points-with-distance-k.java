class Solution {
    public int countPairs(List<List<Integer>> coordinates, int k) {
        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        int ans = 0;
        for (List<Integer> c : coordinates) {
            int x = c.get(0);
            int y = c.get(1);
            for (int a = 0; a <= k; a++)    {
                int b = k - a;
                int x1 = a ^ x;
                int y1 = b ^ y;
                Pair<Integer, Integer> p1 = new Pair<>(x1, y1);
                ans += map.getOrDefault(p1, 0);
            }
            Pair<Integer, Integer> p = new Pair<>(x, y);
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        
        return ans;
    }
}