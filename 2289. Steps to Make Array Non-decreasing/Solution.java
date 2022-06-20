class Solution {
    public int totalSteps(int[] nums) {
        Deque<Integer> q = new LinkedList<>();
        int n = nums.length;
        int[] count = new int[n];
        for (int i = n - 1; i > -1; i--)    {
            int f = 0;
            while (!q.isEmpty() && nums[q.peek()] < nums[i])    {
                f = Math.max(f + 1, count[q.pop()]);
            }
            count[i] = f;
            q.push(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++)
            ans = Math.max(ans, count[i]);
        
        return ans;
    }
}
