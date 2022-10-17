class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int p = 0;
        for (int i = 0; i < k - 1; i++)
            pq.offer(new int[] {i, nums[i]});
        for (int r = k - 1; r < n; r++) {
            pq.offer(new int[] {r, nums[r]});
            while (pq.peek()[0] < r - k + 1)
                pq.poll();
            ans[p++] = pq.peek()[1];
        }
        
        return ans;
    }
}
