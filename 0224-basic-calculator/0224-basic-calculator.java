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
        Deque<Integer> numStack = new LinkedList<>();
        Deque<Character> opStack = new LinkedList<>();
        numStack.push(0);
        int i = 0;
        s = s.replace(" ", "");
        int n = s.length();
        while (i < n)   {
            char c = s.charAt(i);
            if (c == '(')   {
                opStack.push(c);
                i++;
                continue;
            }
            
            if (c == ')')   {
                while (opStack.peek() != '(')
                    calc(opStack, numStack);
                opStack.pop();
                i++;
                continue;
            }
            
            if (Character.isDigit(c))   {
                int num = 0;
                int j = i;
                while (j < n && Character.isDigit(s.charAt(j)))   {
                    num = num * 10 + (s.charAt(j) - '0');
                    j++;
                }
                numStack.push(num);
                i = j;
                continue;
            }
            
            if (i > 0 && (s.charAt(i - 1) == '(' || s.charAt(i - 1) == '+'))
                numStack.push(0);
            
            while (!opStack.isEmpty() && priority.get(opStack.peek()) >= priority.get(c))
                calc(opStack, numStack);
            
            opStack.push(c);
            i++;
            continue;
        }
        while (!opStack.isEmpty())
            calc(opStack, numStack);
        
        return numStack.pop();
    }
    
    void calc(Deque<Character> opStack, Deque<Integer> numStack)    {
        int b = numStack.pop();
        int a = numStack.pop();
        char c = opStack.pop();
        if (c == '+')
            numStack.push(a + b);
        else if (c == '-')
            numStack.push(a - b);
        else if (c == '*')
            numStack.push(a * b);
        else if (c == '/')
            numStack.push(a / b);
    }
}