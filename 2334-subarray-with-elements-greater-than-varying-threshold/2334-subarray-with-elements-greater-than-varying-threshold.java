class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++)    {
            while (!stack.isEmpty() && (i == n || nums[i] <= nums[stack.peek()]))   {
                int pop = stack.pop();
                int prev = stack.isEmpty() ? -1 : stack.peek();
                int len = i - prev - 1;
                if (nums[pop] > (double)threshold / len)
                    return len;
            }
            stack.push(i);
        }
        
        return -1;
    }
}