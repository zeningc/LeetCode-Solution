class MedianFinder {
    PriorityQueue<Integer> minQ;
    PriorityQueue<Integer> maxQ;
    
    public MedianFinder() {
        minQ = new PriorityQueue<>((a, b) -> a - b);
        maxQ = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        if (minQ.size() <= maxQ.size())
        {
            maxQ.offer(num);
            minQ.offer(maxQ.poll());
        }
        else    {
            minQ.offer(num);
            maxQ.offer(minQ.poll());
        }
    }
    
    public double findMedian() {
        if (minQ.size() == maxQ.size())
            return ((double)minQ.peek() + maxQ.peek()) / 2;
        return minQ.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */