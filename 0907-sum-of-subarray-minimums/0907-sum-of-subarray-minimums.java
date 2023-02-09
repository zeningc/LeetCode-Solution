class Solution {
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stack = new LinkedList<>();
        int mod = 1000000007;
        long ans = 0;
        for (int i = 0; i <= arr.length; i++)    {
            while (!stack.isEmpty() && (i == arr.length || arr[stack.peek()] > arr[i]))  {
                int pop = stack.pop();
                ans = (ans + (long)arr[pop] * (i - pop) * (pop - (stack.isEmpty() ? -1 : stack.peek()))) % mod;
            }
            stack.push(i);
        }
        return (int)ans;
    }
}

