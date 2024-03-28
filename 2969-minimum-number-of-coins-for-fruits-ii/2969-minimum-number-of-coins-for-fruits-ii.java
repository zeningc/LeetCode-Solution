class Solution {
    public int minimumCoins(int[] prices) {
        int n = prices.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {2, prices[0]});
        for (int i = 1; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] < i)
                pq.poll();
            int[] cur = pq.peek();
            int p = cur[1];
            pq.offer(new int[] {i * 2 + 2, p + prices[i]});
        }
        while (pq.peek()[0] < n)
            pq.poll();
        return pq.peek()[1];
    }
}