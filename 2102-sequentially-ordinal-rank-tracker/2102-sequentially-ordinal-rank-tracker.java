class SORTracker {
    PriorityQueue<int[]> minPQ;
    PriorityQueue<int[]> maxPQ;
    Map<Integer, String> nameMap;
    int queryCnt;
    int cnt;
    public SORTracker() {
        minPQ = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : nameMap.get(b[0]).compareTo(nameMap.get(a[0])));
        maxPQ = new PriorityQueue<>((a, b) -> a[1] != b[1] ? b[1] - a[1] : nameMap.get(a[0]).compareTo(nameMap.get(b[0])));
        nameMap = new HashMap<>();
        queryCnt = 0;
        cnt = 0;
    }
    
    public void add(String name, int score) {
        int id = cnt++;
        nameMap.put(id, name);
        minPQ.offer(new int[] {id, score});
        maxPQ.offer(minPQ.poll());
    }
    
    public String get() {
        queryCnt++;
        minPQ.offer(maxPQ.poll());
        return nameMap.get(minPQ.peek()[0]);
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */