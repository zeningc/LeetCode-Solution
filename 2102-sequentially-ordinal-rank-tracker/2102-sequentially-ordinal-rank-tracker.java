class SORTracker {
    Map<Integer, String> map;
    Queue<int[]> maxQ;
    Queue<int[]> minQ;
    int idx;
    public SORTracker() {
        map = new HashMap<>();
        minQ = new PriorityQueue<>((a, b) -> a[1] == b[1] ? map.get(b[0]).compareTo(map.get(a[0])) : a[1] - b[1]);
        maxQ = new PriorityQueue<>((a, b) -> b[1] == a[1] ? map.get(a[0]).compareTo(map.get(b[0])) : b[1] - a[1]);
        idx = 0;
    }
    
    public void add(String name, int score) {
        int index = idx;
        idx++;
        map.put(index, name);
        int[] insert = new int[] {index, score};
        int[] peek = minQ.peek();
        if (minQ.isEmpty() || peek[1] > insert[1] || peek[1] == insert[1] && map.get(peek[0]).compareTo(name) < 0) {
            maxQ.offer(insert);
        }
        else    {
            minQ.offer(insert);
            maxQ.offer(minQ.poll());
        }
        
        return;
    }
    
    public String get() {
        String ret = map.get(maxQ.peek()[0]);
        
        minQ.offer(maxQ.poll());
        
        return ret;
    }
    
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */