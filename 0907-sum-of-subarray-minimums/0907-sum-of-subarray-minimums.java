class Solution {
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stk = new LinkedList<>();
        int mod = 1000000007;
        int ans = 0;
        for (int i = 0; i < arr.length; i++)    {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                int pop = stk.pop();
                long left = stk.isEmpty() ? -1 : stk.peek();
                long right = i;
                ans = (int)((ans + (right - pop) * (pop - left) * arr[pop]) % mod);
            }
            stk.push(i);
        }
        
        while (!stk.isEmpty()) {
            int pop = stk.pop();
            long left = stk.isEmpty() ? -1 : stk.peek();
            long right = arr.length;
            ans = (int)((ans + (right - pop) * (pop - left) * arr[pop]) % mod);
        }
        
        return ans;
    }
}