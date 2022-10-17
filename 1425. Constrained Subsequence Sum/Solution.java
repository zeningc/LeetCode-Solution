class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        Arrays.fill(dp, Integer.MIN_VALUE / 2);
        for (int i = 0; i < n; i++) {
            while (!pq.isEmpty() && pq.peek()[0] < i - k)
                pq.poll();
            dp[i] = nums[i] + Math.max(0, (pq.isEmpty() ? 0 : pq.peek()[1]));
            pq.offer(new int[] {i, dp[i]});
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
