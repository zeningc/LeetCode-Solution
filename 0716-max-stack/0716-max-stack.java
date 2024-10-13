class MaxStack {
    TreeMap<Integer, List<Node>> rankMap;
    Node root;
    public MaxStack() {
        root = new Node(-1);
        root.nxt = root;
        root.pre = root;
        rankMap = new TreeMap<>();
    }
    
    public void push(int x) {
        Node node = new Node(x);
        rankMap.computeIfAbsent(x, a -> new LinkedList<>()).add(0, node);
        addToHead(node);
    }
    
    public int pop() {
        Node node = removeHead();
        rankMap.get(node.val).remove(0);
        if (rankMap.get(node.val).isEmpty())
            rankMap.remove(node.val);
        return node.val;
    }
    
    public int top() {
        return root.nxt.val;
    }
    
    public int peekMax() {
        return rankMap.lastEntry().getKey();
    }
    
    public int popMax() {
        int maxKey = peekMax();
        Node node = rankMap.lastEntry().getValue().remove(0);
        if (rankMap.get(node.val).isEmpty())
            rankMap.remove(node.val);
        removeNode(node);
        return maxKey;
    }
    
    void addToHead(Node node)    {
        node.nxt = root.nxt;
        node.pre = root;
        root.nxt.pre = node;
        root.nxt = node;
    }
    
    Node removeNode(Node nodeToRemove)  {
        nodeToRemove.pre.nxt = nodeToRemove.nxt;
        nodeToRemove.nxt.pre = nodeToRemove.pre;
        nodeToRemove.pre = null;
        nodeToRemove.nxt = null;
        return nodeToRemove;
    }
    
    Node removeHead()   {
        Node nodeToRemove = root.nxt;
        return removeNode(nodeToRemove);
    }
}

class Node  {
    Node pre;
    Node nxt;
    int val;
    
    public Node(int val)   {
        this.val = val;
        pre = null;
        nxt = null;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */