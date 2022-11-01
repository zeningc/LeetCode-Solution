class LRUCache {
    Node root;
    Map<Integer, Node> map;
    int capacity;
    public LRUCache(int capacity) {
        root = new Node(-1, -1);
        root.next = root;
        root.prev = root;
        map = new HashMap<>();
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        Node node = map.get(key);
        deleteFromList(node);
        addToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key))   {
            Node node = map.get(key);
            node.value = value;
            deleteFromList(node);
            addToHead(node);
            return;
        }
        
        if (map.size() == capacity) {
            Node nodeToDelete = root.prev;
            deleteFromList(nodeToDelete);
            map.remove(nodeToDelete.key);
        }
        Node node = new Node(key, value);
        map.put(key, node);
        addToHead(node);
    }
    
    private void deleteFromList(Node node)  {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void addToHead(Node node)   {
        node.next = root.next;
        node.prev = root;
        root.next.prev = node;
        root.next = node;
    }
}

class Node  {
    Node prev;
    Node next;
    int key;
    int value;
    
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