class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        int[] p = new int[k];
        int[] ans = new int[n];
        ans[0] = 1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int i = 0; i < k; i++) {
            pq.offer(new int[] {i, ans[p[i]] * primes[i]});
        }
        for (int i = 0; i < n - 1; i++) {
            int[] curr = pq.peek();
            int idx = curr[0];
            int val = curr[1];
            ans[i + 1] = val;
            while (pq.peek()[1] == val) {
                idx = pq.poll()[0];
                p[idx]++;
                pq.offer(new int[] {idx, ans[p[idx]] * primes[idx]});
            }
            
        }
        return ans[n - 1];
    }
}

