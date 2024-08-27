class Solution {
    public int maxSumMinProduct(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        long ans = nums[0] * nums[0];
        int n = nums.length;
        long[] presum = new long[n + 1];
        for (int i = 0; i < nums.length; i++)   {
            presum[i + 1] = presum[i] + nums[i];
        }
        
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] >= nums[i]))   {
                int pop = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;
                ans = Math.max(ans, (presum[right] - presum[left + 1]) * nums[pop]);
            }
            stack.push(i);
        }
        
        return (int)(ans % 1000000007);
    }
}