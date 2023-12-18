class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        Deque<Integer> stack = new LinkedList<>();
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] <= nums[i]))   {
                int pop = stack.pop();
                if (nums[pop] >= left && nums[pop] <= right)    {
                    int lo = stack.isEmpty() ? -1 : stack.peek();
                    int hi = i;
                    ans += (pop - lo) * (hi - pop);
                }
            }
            
            stack.push(i);
        }
        
        return ans;
    }
}