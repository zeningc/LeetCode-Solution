class Solution {
    public int minimumDistance(int[][] points) {
        int n = points.length;
        Map<Integer, TreeMap<Integer, Integer>> maxMaps = new HashMap<>();
        Map<Integer, TreeMap<Integer, Integer>> minMaps = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> maps = new HashMap<>();
        for (int state = 0; state < (1 << 2); state++) {
            maxMaps.put(state, new TreeMap<>((a, b) -> b - a));
            minMaps.put(state, new TreeMap<>());
            maps.put(state, new HashMap<>());
        }
        for (int state = 0; state < (1 << 2); state++)  {
            for (int i = 0; i < n; i++) {
                int cur = 0;
                for (int j = 0; j < 2; j++) {
                    if ((state & (1 << j)) != 0)
                        cur += points[i][j];
                    else
                        cur -= points[i][j];
                }
                TreeMap<Integer, Integer> m;
                m = maxMaps.get(state);
                m.put(cur, m.getOrDefault(cur, 0) + 1);
                m = minMaps.get(state);
                m.put(cur, m.getOrDefault(cur, 0) + 1);
                maps.get(state).put(i, cur);
            }
            
        }
        int ans = getMaxDist(maxMaps, minMaps, maps, 0);
        
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, getMaxDist(maxMaps, minMaps, maps, i));
        }
        
        return ans;
    }
    
    int getMaxDist(Map<Integer, TreeMap<Integer, Integer>> maxMaps, Map<Integer, TreeMap<Integer, Integer>> minMaps, Map<Integer, Map<Integer, Integer>> maps, int idx)    {
        int ret = 0;
        for (int state = 0; state < (1 << 2); state++)  {
            int cur = maps.get(state).get(idx);
            int max = maxMaps.get(state).firstKey();
            int min = minMaps.get(state).firstKey();
            if (max == cur && maxMaps.get(state).get(max) == 1)
                max = maxMaps.get(state).higherKey(cur);
            if (min == cur && minMaps.get(state).get(min) == 1)
                min = minMaps.get(state).higherKey(cur);
            ret = Math.max(ret, max - min);
        }
        return ret;
    }
}