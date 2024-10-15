class Solution {
    Map<Character, Integer> priority = Map.of(
        '(', 0,
        ')', 0,
        '+', 1,
        '-', 1,
        '*', 2,
        '/', 2
    );
    public int calculate(String s) {
        Deque<Character> opStack = new LinkedList<>();
        Deque<Integer> numStack = new LinkedList<>();
        numStack.push(0);
        s = s.replace(" ", "");
        int idx = 0;
        int n = s.length();
        while (idx < n) {
            char c = s.charAt(idx);
            
            if (c == '(')   {
                opStack.push('(');
                idx++;
                continue;
            }
            
            if (c == ')')   {
                while (opStack.peek() != '(')   {
                    calc(numStack, opStack);
                }
                opStack.pop();
                idx++;
                continue;
            }
            
            if (Character.isDigit(c))   {
                int j = idx;
                int num = 0;
                while (j < n && Character.isDigit(s.charAt(j))) {
                    num = num * 10 + (s.charAt(j) - '0');
                    j++;
                }
                numStack.push(num);
                idx = j;
                continue;
            }
            
            
            if (c == '-' || c == '+' || c == '*' || c == '/')   {
                if (idx > 0 && (s.charAt(idx - 1) == '(' || s.charAt(idx - 1) == '+' || s.charAt(idx - 1) == '-'))
                    numStack.push(0);
                while (!opStack.isEmpty() && priority.get(c) <= priority.get(opStack.peek()))
                    calc(numStack, opStack);
                opStack.push(c);
                idx++;
                continue;
            }
            
            
        }
        
        while (!opStack.isEmpty())
            calc(numStack, opStack);
        
        return numStack.pop();
    }
    
    void calc(Deque<Integer> nums, Deque<Character> ops)    {
        int num2= nums.pop();
        int num1 = nums.pop();
        char c = ops.pop();
        if (c == '+')   {
            nums.push(num1 + num2);
            return;
        }
        if (c == '-')   {
            nums.push(num1 - num2);
            return;
        }
        if (c == '*')   {
            nums.push(num1 * num2);
            return;
        }
        nums.push(num1 / num2);
    }
}