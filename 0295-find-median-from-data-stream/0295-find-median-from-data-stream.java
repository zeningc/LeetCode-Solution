class MedianFinder {
    PriorityQueue<Integer> maxPQ;
    PriorityQueue<Integer> minPQ;
    public MedianFinder() {
        minPQ = new PriorityQueue<>();
        maxPQ = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
         if (maxPQ.size() == minPQ.size())  {
             if (!minPQ.isEmpty() && num > minPQ.peek())    {
                 minPQ.offer(num);
                 num = minPQ.poll();
             }
             maxPQ.offer(num);
             return;
         }
        
        if (num < maxPQ.peek()) {
            maxPQ.offer(num);
            num = maxPQ.poll();
        }
        minPQ.offer(num);
    }
    
    public double findMedian() {
        if (maxPQ.size() == minPQ.size())
            return ((double)maxPQ.peek() + minPQ.peek()) / 2;
        return maxPQ.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */