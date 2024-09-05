class Solution {
    public int sumImbalanceNumbers(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = map.getOrDefault(nums[i] + 1, -1);
            map.put(nums[i], i);
        }
        map.clear();
        int[] nxt = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            nxt[i] = Math.min(map.getOrDefault(nums[i], n), map.getOrDefault(nums[i] + 1, n));
            map.put(nums[i], i);
        }
        int[] preGreater = new int[n];
        int[] nxtGreater = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || nums[stack.peek()] <= nums[i]))   {
                int pop = stack.pop();
                preGreater[pop] = Math.max(pre[pop], (stack.isEmpty() || nums[stack.peek()] - nums[pop] <= 1) ? -1 : stack.peek());
                nxtGreater[pop] = Math.min(nxt[pop], i < n && nums[i] - nums[pop] > 1 ? i : n);
            }
            stack.push(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (i - pre[i]) * (nxt[i] - i) - (i - preGreater[i]) * (nxtGreater[i] - i);
        }
        
        return ans;
    }
}