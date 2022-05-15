class CountIntervals {
    TreeMap<Integer, Integer> map;
    int cnt;
    public CountIntervals() {
        map = new TreeMap<>();
        cnt = 0;
    }
    
    public void add(int left, int right) {
        TreeMap<Integer, Integer> m = map;
        int c = cnt;
        if (map.floorKey(right) == null || map.get(map.floorKey(right)) < left)    {
            cnt += right - left + 1;
            map.put(left, right);
            return;
        }
        
        int start = left;
        int end = right;
        
        Integer prev = map.floorKey(start);
        if (prev != null)   {
            int prevEnd = map.get(prev);
            if (prevEnd >= start)    {
                start = prev;
                end = Math.max(prevEnd, end);
                cnt -= prevEnd - prev + 1;
                map.remove(prev);
            }
        }
        
        while (map.ceilingKey(start) != null)   {
            int next = map.ceilingKey(start);
            int nextEnd = map.get(next);
            if (end >= next)    {
                end = Math.max(end, nextEnd);
                map.remove(next);
                cnt -= nextEnd - next + 1;
                continue;
            }
            break;
        }
        
        map.put(start, end);
        cnt += end - start + 1;
        
    }
    
    public int count() {
        return cnt;
    }
}
