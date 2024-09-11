class SORTracker {
    PriorityQueue<int[]> minPQ;
    PriorityQueue<int[]> maxPQ;
    Map<Integer, String> idToLocation;
    int cnt;
    int queryCnt;
    public SORTracker() {
        cnt = 0;
        queryCnt = 0;
        idToLocation = new HashMap<>();
        minPQ = new PriorityQueue<>((a, b) -> b[1] == a[1] ? idToLocation.get(b[0]).compareTo(idToLocation.get(a[0])) : a[1] - b[1]);
        maxPQ = new PriorityQueue<>((a, b) -> b[1] == a[1] ? idToLocation.get(a[0]).compareTo(idToLocation.get(b[0])) : b[1] - a[1]);
    }
    
    public void add(String name, int score) {
        int locationId = cnt;
        idToLocation.put(locationId, name);
        int[] cur = new int[] {locationId, score};
        if (!minPQ.isEmpty() && (score > minPQ.peek()[1] || score == minPQ.peek()[1] && name.compareTo(idToLocation.get(minPQ.peek()[0])) < 0))   {
            minPQ.offer(cur);
            cur = minPQ.poll();
        }
        maxPQ.offer(cur);
        cnt++;
    }
    
    public String get() {
        while (minPQ.size() < queryCnt + 1)
            minPQ.offer(maxPQ.poll());
        queryCnt++;
        return idToLocation.get(minPQ.peek()[0]);
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 
 */