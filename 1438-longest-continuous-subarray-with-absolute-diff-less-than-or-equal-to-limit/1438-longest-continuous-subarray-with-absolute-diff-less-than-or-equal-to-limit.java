class Solution {
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length;
        Deque<Integer> maxQ = new LinkedList<>();
        Deque<Integer> minQ = new LinkedList<>();
        int l = 0;
        int ans = 0;
        for (int r = 0; r < n; r++) {
            while (!maxQ.isEmpty() && nums[maxQ.peekLast()] <= nums[r])
                maxQ.pollLast();
            maxQ.offerLast(r);
            while (!minQ.isEmpty() && nums[minQ.peekLast()] >= nums[r])
                minQ.pollLast();
            minQ.offerLast(r);
            
            while (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > limit) {
                while (maxQ.peekFirst() <= l)
                    maxQ.pollFirst();
                while (minQ.peekFirst() <= l)
                    minQ.pollFirst();
                l++;
            }
            
            ans = Math.max(ans, r - l + 1);
        }
        
        return ans;
    }
}