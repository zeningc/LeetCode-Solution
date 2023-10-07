class Solution {
    public int totalSteps(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new LinkedList<>();
        int[] t = new int[n];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            int cnt = 0;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
            {
                int pop = stack.pop();
                cnt = Math.max(cnt + 1, t[pop]);
            }
            t[i] = cnt;
            ans = Math.max(ans, t[i]);
            stack.push(i);
        }
        
        return ans;
    }
}