class Solution {
    public int scoreOfParentheses(String s) {
        Deque<int[]> stack = new LinkedList<>();
        int left = 0;
        for (char c : s.toCharArray())  {
            if (c == '(')   {
                left++;
            }
            else if (c == ')')  {
                left--;
                if (!stack.isEmpty() && stack.peek()[0] == left + 1)   {
                    stack.push(new int[] {left, stack.pop()[1] * 2});
                }
                else    {
                    stack.push(new int[] {left, 1});
                }
                
                int sum = 0;
                while (!stack.isEmpty() && stack.peek()[0] == left) {
                    sum += stack.pop()[1];
                }
                stack.push(new int[] {left, sum});
            }
        }
        
        return stack.pop()[1];
    }
}
