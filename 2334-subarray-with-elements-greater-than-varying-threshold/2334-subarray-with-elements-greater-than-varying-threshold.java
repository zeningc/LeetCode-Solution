class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++)
        {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] >= nums[i]))
            {
                int cur = stack.pop();
                int pre = stack.isEmpty() ? -1 : stack.peek();
                int len = (i - pre - 1);
                if (threshold / len < nums[cur])
                    return len;
            }
            stack.push(i);
        }
        
        return -1;
    }
}
