class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        Deque<int[]> q = new LinkedList<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            while (!q.isEmpty() && q.peekFirst()[0] < i - k)
                q.pollFirst();
            int curr = nums[i];
            if (!q.isEmpty())
                curr = Math.max(curr, curr + q.peekFirst()[1]);
            ans = Math.max(curr, ans);
            while (!q.isEmpty() && q.peekLast()[1] < curr)
                q.pollLast();
            q.offerLast(new int[] {i, curr});
        }
        
        return ans;
    }
}
