class Solution {
    public String robotWithString(String s) {
        int n = s.length();
        int[] min = new int[n];
        for (int i = n - 1; i >= 0; i--)
        {
            char c = s.charAt(i);
            if (i == n - 1)
            {
                min[i] = c;
                continue;
            }
            
            min[i] = Math.min(c, min[i + 1]);
        }
        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i <= n; i++)
        {
            while (!stack.isEmpty() && (i == n || stack.peek() <= min[i]))
            {
                sb.append(stack.pop());
            }
            if (i != n)
                stack.push(s.charAt(i));
        }
        
        return sb.toString();
    }
}