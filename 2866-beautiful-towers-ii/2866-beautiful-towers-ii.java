class Solution {
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        int[] smallerIndexToLeft = new int[n];
        int[] smallerIndexToRight = new int[n];

        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (
                i == n || maxHeights.get(stack.peek()) > maxHeights.get(i)
            ))  {
                int pop = stack.pop();
                smallerIndexToLeft[pop] = stack.isEmpty() ? -1 : stack.peek();
                smallerIndexToRight[pop] = i;
            }
            stack.push(i);
        }
        
        long[] leftSum = new long[n];
        for (int i = 0; i < n; i++) {
            leftSum[i] += (long)(i - smallerIndexToLeft[i]) * maxHeights.get(i);
            if (smallerIndexToLeft[i] != -1)
                leftSum[i] += leftSum[smallerIndexToLeft[i]];
        }
        
        long[] rightSum = new long[n];
        for (int i = n - 1; i >= 0; i--)    {
            rightSum[i] += (long)(smallerIndexToRight[i] - i) * maxHeights.get(i);
            if (smallerIndexToRight[i] != n)
                rightSum[i] += rightSum[smallerIndexToRight[i]];
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(leftSum[i] + rightSum[i] - maxHeights.get(i), ans);
        }
        
        return ans;
    }
}