class Solution {
    public int numRescueBoats(int[] people, int limit) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int p : people)
            map.put(p, map.getOrDefault(p, 0) + 1);
        
        int ans = 0;
        while (map.size() > 0)  {
            Integer a = map.firstKey();
            Integer b = map.floorKey(limit - a);
            if (b == null || a.equals(b))  {
                int left = map.get(a);
                if (2 * a <= limit) {
                    ans += left / 2 + left % 2;
                }
                else    {
                    ans += left;
                }
                map.remove(a);
            }
            else    {
                int left = Math.min(map.get(a), map.get(b));
                ans += left;
                map.put(a, map.getOrDefault(a, 0) - left);
                map.put(b, map.getOrDefault(b, 0) - left);
                if (map.containsKey(a) && map.get(a) == 0)
                    map.remove(a);
                if (map.containsKey(b) && map.get(b) == 0)
                    map.remove(b);
            }
        }
        
        return ans;
    }
}
