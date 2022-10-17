class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            presum[i + 1] = presum[i] + nums[i];
        }
        
        int ans = Integer.MAX_VALUE;
        Deque<Integer> q = new LinkedList<>();
        q.offer(0);
        for (int i = 1; i <= n; i++) {
            while (!q.isEmpty() && presum[i] - presum[q.peekFirst()] >= k)  {
                ans = Math.min(ans, i - q.pollFirst());
            }
            
            while (!q.isEmpty() && presum[q.peekLast()] >= presum[i])
                q.pollLast();
            q.offer(i);
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
