class Solution {
    public long subArrayRanges(int[] nums) {
        long ans = 0;
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] >= nums[i]))   {
                int pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                ans -= (long)nums[pop] * (pop - left) * (right - pop);
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] <= nums[i]))   {
                int pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                ans += (long)nums[pop] * (pop - left) * (right - pop);
            }
            stack.push(i);
        }
        
        return ans;
    }
}