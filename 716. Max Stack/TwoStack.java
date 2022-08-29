class MaxStack {
    Deque<Integer> stack;
    Deque<Integer> maxStack;
    public MaxStack() {
        stack = new LinkedList<>();
        maxStack = new LinkedList<>();
    }
    
    public void push(int x) {
        stack.push(x);
        int max;
        if (maxStack.isEmpty()) {
            max = x;
        }
        else    {
            max = Math.max(maxStack.peek(), x);
        }
        maxStack.push(max);
    }
    
    public int pop() {
        
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int max = maxStack.peek();
        Deque<Integer> cache = new LinkedList<>();
        while (!stack.isEmpty())    {
            int currPeek = stack.peek();
            int maxPeek = maxStack.peek();
            stack.pop();
            maxStack.pop();
            if (currPeek == max)    {
                while (!cache.isEmpty())    {
                    push(cache.pop());
                }
                return max;
            }
            cache.push(currPeek);
        }
        
        return 0;
    }
}
