class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int ans = nums[0];
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, nums[0]});
        for (int i = 1; i < n; i++) {
            while (!q.isEmpty() && q.peekFirst()[0] + k < i)
                q.pollFirst();
            int curr = (q.isEmpty() ? 0 : q.peekFirst()[1]) + nums[i];
            
            while (!q.isEmpty() && q.peekLast()[1] <= curr)
                q.pollLast();
            
            q.offerLast(new int[] {i, curr});
            if (i == n - 1)
                ans = curr;
        }
        
        
        return ans;
    }
}