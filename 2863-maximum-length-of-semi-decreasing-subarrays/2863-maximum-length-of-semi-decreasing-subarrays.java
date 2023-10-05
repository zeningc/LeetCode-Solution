public class Solution {
    public int maxSubarrayLength(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;

        for (int i = n - 1; i >= 0; i--) {
            if (stack.isEmpty() || nums[i] < nums[stack.peek()]) {
                stack.push(i);
            }
        }

        int r = 0;
        int m = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() <= i) {
                stack.pop();
            }

            if (nums[i] > m) {
                m = nums[i];

                while (!stack.isEmpty() && nums[stack.peek()] < m) {
                    r = Math.max(r, stack.peek() - i + 1);
                    stack.pop();
                }
            }
        }

        return r;
    }
}