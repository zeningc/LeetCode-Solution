class Solution {
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        long[] ans = new long[5];
        int[][] offset = new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        for (int[] c : coordinates) {
            int x = c[0];
            int y = c[1];
            for (int[] d : offset)  {
                int nx = x + d[0];
                int ny = y + d[1];
                if (nx >= m || ny >= n || nx - 1 < 0 || ny - 1 < 0)
                    continue;
                Pair<Integer, Integer> p = new Pair<>(nx, ny);
                map.put(p, map.getOrDefault(p, 0) + 1);
            }
        }
        ans[0] = (long)(m - 1) * (n - 1);
        for (int v : map.values())   {
            ans[v]++;
            ans[0]--;
        }
        return ans;
    }
}