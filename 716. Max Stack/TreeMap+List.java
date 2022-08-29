class MaxStack {
    TreeMap<Integer, List<Node>> treeMap;
    Node root;
    public MaxStack() {
        root = new Node(-1);
        root.prev = root;
        root.next = root;
        treeMap = new TreeMap<>((a, b) -> b - a);
    }
    
    public void push(int x) {
        Node node = new Node(x);
        node.next = root.next;
        node.prev = root;
        root.next.prev = node;
        root.next = node;
        if (!treeMap.containsKey(x))
            treeMap.put(x, new LinkedList<>());
        treeMap.get(x).add(node);
    }
    
    public int pop() {
        Node node = root.next;
        node.prev.next = node.next;
        node.next.prev = node.prev;
        int val = node.val;
        List<Node> list = treeMap.get(val);
        list.remove(list.size() - 1);
        if (list.isEmpty())
            treeMap.remove(val);
        return val;
    }
    
    public int top() {
        return root.next.val;
    }
    
    public int peekMax() {
        return treeMap.firstKey();
    }
    
    public int popMax() {
        int val = treeMap.firstKey();
        List<Node> list = treeMap.get(val);
        Node node = list.get(list.size() - 1);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        list.remove(list.size() - 1);
        if (list.isEmpty())
            treeMap.remove(val);
        return val;
    }
}

class Node  {
    int val;
    Node prev;
    Node next;
    
    public Node(int val)    {
        this.val = val;
    }
}
