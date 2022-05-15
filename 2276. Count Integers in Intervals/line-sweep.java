class CountIntervals {
    TreeMap<Integer, Integer> map;
    int ans;
    public CountIntervals() {
        ans = 0;
        map = new TreeMap<>();
    }
    
    public void add(int left, int right) {
        map.put(left, map.getOrDefault(left, 0) + 1);
        map.put(right + 1, map.getOrDefault(right + 1, 0) - 1);
        
        int cnt = 0;
        int prev = 0;
        ans = 0;
        for (Map.Entry<Integer, Integer> e : map.entrySet())    {
            int key = e.getKey();
            int val = e.getValue();
            if (cnt == 0)   {
                prev = key;
                cnt += val;
                continue;
            }
            
            cnt += val;
            
            if (cnt == 0)   {
                ans += key - prev;
            }
        }
    }
    
    public int count() {
        return ans;
    }
}
