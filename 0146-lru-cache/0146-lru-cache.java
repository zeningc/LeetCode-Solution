class LRUCache {
    Map<Integer, Node> keyToNode;
    Node root;
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToNode = new HashMap<>();
        root = new Node(-1, -1);
        root.nxt = root;
        root.pre = root;
    }
    
    public int get(int key) {
        if (!keyToNode.containsKey(key))
            return -1;
        Node node = keyToNode.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        if (!keyToNode.containsKey(key))    {
            Node node = new Node(key, value);
            moveToHead(node);
            keyToNode.put(key, node);
            capacity--;
            if (capacity < 0)   {
                Node nodeToRemove = root.pre;
                nodeToRemove.pre.nxt = nodeToRemove.nxt;
                nodeToRemove.nxt.pre = nodeToRemove.pre;
                keyToNode.remove(nodeToRemove.key);
            }
            capacity = Math.max(0, capacity);
            return;
        }
        
        Node node = keyToNode.get(key);
        node.val = value;
        moveToHead(node);
    }
    
    private void moveToHead(Node node)    {
        if (node == null)
            return;
        if (node.pre != null)
            node.pre.nxt = node.nxt;
        if (node.nxt != null)
            node.nxt.pre = node.pre;
        
        node.nxt = root.nxt;
        node.pre = root;
        root.nxt.pre = node;
        root.nxt = node;
    }
}

class Node    {
    Node pre;
    Node nxt;
    int val;
    int key;
    
    public Node(int key, int val)    {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */