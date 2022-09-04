class FreqStack {
    Map<Integer, Integer> freq;
    Map<Integer, Deque<Integer>> stackMap;
    int maxFreq;
    public FreqStack() {
        freq = new HashMap<>();
        stackMap = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        int f = freq.getOrDefault(val, 0);
        freq.put(val, ++f);
        
        if (f > maxFreq)
            maxFreq = f;
        
        if (!stackMap.containsKey(f))
            stackMap.put(f, new LinkedList<>());
        
        stackMap.get(f).push(val);
    }
    
    public int pop() {
        int val = stackMap.get(maxFreq).pop();
        freq.put(val, freq.get(val) - 1);
        if (stackMap.get(maxFreq).isEmpty())
            maxFreq--;
        
        return val;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */