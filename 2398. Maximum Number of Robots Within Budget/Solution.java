class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int ans = 0;
        int l = 0;
        int n = runningCosts.length;
        Deque<Integer> q = new LinkedList<>();
        long sum = 0;
        for (int r = 0; r < n; r++) {
            sum += runningCosts[r];
            while (!q.isEmpty() && chargeTimes[r] >= chargeTimes[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(r);
            while (l <= r && sum * (r - l + 1) + chargeTimes[q.peekFirst()] > budget)    {
                sum -= runningCosts[l];
                if (q.peekFirst() == l)
                    q.pollFirst();
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        
        return ans;
    }
}

