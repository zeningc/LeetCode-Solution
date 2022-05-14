class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (int[] r : ranges)  {
            map.put(r[0], map.getOrDefault(r[0], 0) + 1);
            map.put(r[1], map.getOrDefault(r[1], 0) - 1);
        }
        
        int cnt = 0;
        int prev = left - 1;
        for (int key : map.keySet())    {
            if (key < left) {
                cnt += map.get(key);
                continue;
            }
            
            if (cnt == 0)   {
                if (prev < key - 1)
                    return false;
            }
            
            if (key >= right)
                return true;
            
            cnt += map.get(key);
            
            if (cnt == 0)   {
                prev = key;
                continue;
            }
            
        }
        return false;
    }
}
