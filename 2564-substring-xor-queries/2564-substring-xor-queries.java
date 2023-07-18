class Solution {
    public int[][] substringXorQueries(String s, int[][] queries) {
        Map<Long, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++)    {
            long cur = 0;
            for (int j = i; j < Math.min(i + 30, s.length()); j++)    {
                cur = cur * 2 + (s.charAt(j) - '0');
                if (!map.containsKey(cur))  {
                    map.put(cur, new int[] {i, j});
                    continue;
                }
                int[] last = map.get(cur);
                if (last[1] - last[0] > j - i)
                    map.put(cur, new int[] {i, j});
            }
        }
        
        int[][] ret = new int[queries.length][];
        for (int i = 0; i < queries.length; i++)    {
            int target = (queries[i][0] ^ queries[i][1]);
            ret[i] = map.getOrDefault((long)target, new int[] {-1, -1});
        }
        
        return ret;
    }
}