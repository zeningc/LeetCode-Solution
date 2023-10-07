class Solution {
    public long maximumBooks(int[] books) {
        int n = books.length;
        long[] left = new long[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++)
        {
            while (!stack.isEmpty() && books[i] - (i - stack.peek()) <= books[stack.peek()])
            {
                stack.pop();
            }
            long a1 = books[i] - (i - (stack.isEmpty() ? -1 :stack.peek()) - 1);
            if (a1 < 0)
            {
                left[i] = (long)books[i] * (books[i] + 1) / 2;
            }
            else
            {
                left[i] = (stack.isEmpty() ? 0 : left[stack.peek()]);
                left[i] += ((long)books[i] + a1) * (i - (stack.isEmpty() ? -1 :stack.peek())) / 2;
            }
            stack.push(i);
        }
        
        
        
        long ans = 0;
        for (int i = 0; i < n; i++)
            ans = Math.max(ans, left[i]);
        
        return ans;
    }
}