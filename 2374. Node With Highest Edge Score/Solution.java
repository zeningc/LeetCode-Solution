class Solution {
    public int edgeScore(int[] edges) {
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++)  {
            map.put(edges[i], map.getOrDefault(edges[i], 0L) + i);
        }
        int ans = Integer.MAX_VALUE;
        long maxVal = Long.MIN_VALUE;
        for (int key : map.keySet())    {
            long val = map.get(key);
            if (val > maxVal || val == maxVal && key < ans) {
                maxVal = val;
                ans = key;
            }
        }
        
        return ans;
    }
}
