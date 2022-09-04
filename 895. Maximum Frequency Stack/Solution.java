class FreqStack {
    TreeSet<Pair> stackMap;
    Map<Integer, LinkedList<Integer>> timeSequenceMap;
    int t;
    public FreqStack() {
        stackMap = new TreeSet<>((a, b) -> a.freq == b.freq ? timeSequenceMap.get(b.val).peekLast() - timeSequenceMap.get(a.val).peekLast() : b.freq - a.freq);
        timeSequenceMap = new HashMap<>();
        t = 0;
    }
    
    public void push(int val) {
        Pair p = new Pair(val, timeSequenceMap.containsKey(val) ? timeSequenceMap.get(val).size() : 0);
        
        if (stackMap.contains(p))
            stackMap.remove(p);
        
        if (!timeSequenceMap.containsKey(val))  {
            timeSequenceMap.put(val, new LinkedList<>());
        }
        
        timeSequenceMap.get(val).addLast(t++);
        
        p.freq++;
        
        stackMap.add(p);
    }
    
    public int pop() {
        Pair p = stackMap.pollFirst();
        stackMap.remove(p);
        timeSequenceMap.get(p.val).pollLast();
        if (p.freq > 1)   {
            p.freq--;
            stackMap.add(p);
        }
        
        return p.val;
    }
}

class Pair  {
    int val;
    int freq;
    
    public Pair(int val, int freq)  {
        this.val = val;
        this.freq = freq;
    }
    
    @Override
    public boolean equals(Object o)    {
        if (o == this)
            return true;
        
        if (!(o instanceof Pair))
            return false;
        
        Pair p = (Pair)o;
        
        return p.val == this.val && p.freq == this.freq;
    }
    
    @Override
    public int hashCode()   {
        return this.val * 1000000007 + this.freq;
    }
}
