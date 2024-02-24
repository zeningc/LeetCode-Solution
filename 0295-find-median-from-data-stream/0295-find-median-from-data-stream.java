class MedianFinder {
    Queue<Integer> maxQ;
    Queue<Integer> minQ;
    public MedianFinder() {
        maxQ = new PriorityQueue<>((a, b) -> b - a);
        minQ = new PriorityQueue<>((a, b) -> a - b);
    }
    
    public void addNum(int num) {
        int n = maxQ.size() + minQ.size() + 1;
        if (n % 2 == 0) {
            maxQ.offer(num);
            minQ.offer(maxQ.poll());
        }
        else    {
            minQ.offer(num);
            maxQ.offer(minQ.poll());
        }
    }
    
    public double findMedian() {
        Queue<Integer> mx = maxQ;
        Queue<Integer> mn = minQ;
        if ((maxQ.size() + minQ.size()) % 2 == 0)
            return ((double)maxQ.peek() + minQ.peek()) / 2.0;
        return (double)maxQ.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */