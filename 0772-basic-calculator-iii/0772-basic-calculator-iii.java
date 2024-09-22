class Solution {
    Map<Character, Integer> priority = Map.of(
        '+', 1,
        '-', 1,
        '*', 2,
        '/', 2
    );
    public int calculate(String s) {
        Deque<Character> ops = new LinkedList<>();
        Deque<Integer> nums = new LinkedList<>();
        nums.push(0);
        s.replace(" ", "");
        
        for (int i = 0; i < s.length(); i++)    {
            char c = s.charAt(i);
            if (c == '(')   {
                ops.push('(');
                continue;
            }
            
            if (c == ')')   {
                while (ops.peek() != '(')   {
                    calc(ops, nums);
                }
                ops.pop();
                continue;
            }
            
            if (Character.isDigit(c)) {
                int j = i;
                int num = 0;
                while (j < s.length() && Character.isDigit(s.charAt(j)))  {
                    num = num * 10 + s.charAt(j) - '0';
                    j++;
                }
                nums.push(num);
                i = j - 1;
                continue;
            }
            
            while (!ops.isEmpty() && ops.peek() != '(' && ops.peek() != ')' && priority.get(ops.peek()) >= priority.get(c))
                calc(ops, nums);
            
            ops.push(c);
        }
        
        while (!ops.isEmpty())
            calc(ops, nums);
        
        return nums.pop();
    }
    
    void calc(Deque<Character> ops, Deque<Integer> nums)    {
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