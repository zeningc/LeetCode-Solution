class LRUCache {
    Map<Integer, Node> map;
    Node root;
    int cnt;
    int capacity;
    public LRUCache(int capacity) {
        cnt = 0;
        root = new Node(-1, -1);
        root.pre = root;
        root.nxt = root;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        removeNode(node);
        addToFirst(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key) && cnt == capacity)  {
                Node nodeToRemove = root.pre;
                removeNode(nodeToRemove);
                map.remove(nodeToRemove.key);
                cnt--;
        }
        cnt = map.containsKey(key) ? cnt : cnt + 1;
        Node nodeToAdd = map.containsKey(key) ? map.get(key) : new Node(key, value);
        nodeToAdd.value = value;
        map.put(key, nodeToAdd);
        removeNode(nodeToAdd);
        addToFirst(nodeToAdd);
    }
    
    void addToFirst(Node node)  {
        node.pre = root;
        node.nxt = root.nxt;
        root.nxt.pre = node;
        root.nxt = node;
    }
    
    Node removeNode(Node node)  {
        if (node.pre != null && node.nxt != null)   {
            node.pre.nxt = node.nxt;
            node.nxt.pre = node.pre;
        }
        node.pre = null;
        node.nxt = null;
        return node;
    }
}

class Node  {
    int key;
    int value;
    Node pre;
    Node nxt;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */