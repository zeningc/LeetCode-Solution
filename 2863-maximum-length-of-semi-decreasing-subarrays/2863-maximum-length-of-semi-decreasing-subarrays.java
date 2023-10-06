class Solution {
    public int maxSubarrayLength(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (stack.isEmpty() || nums[stack.peek()] < nums[i])
                stack.push(i);
        int ans = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
            {
                ans = Math.max(ans, i - stack.peek() + 1);
                stack.pop();
            }
        }
        
        return ans;
    }
}