class AllOne {
    Map<String, Node> keyToNodeMap;
    Node head;
    public AllOne() {
        head = new Node(-1);
        head.nxt = head;
        head.pre = head;
        keyToNodeMap = new HashMap<>();
    }
    
    public void inc(String key) {
        if (!keyToNodeMap.containsKey(key)) {
            if (head.nxt.value != 1)
                insertNode(head, new Node(1));
            
            Node node = head.nxt;
            node.keys.add(key);
            keyToNodeMap.put(key, node);
            return;
        }
        
        Node pre = keyToNodeMap.get(key);
        if (pre.nxt.value != pre.value + 1)
            insertNode(pre, new Node(pre.value + 1));
        Node cur = pre.nxt;
        pre.keys.remove(key);
        if (pre.keys.isEmpty())
            removeNode(pre);
        cur.keys.add(key);
        keyToNodeMap.put(key, cur);
    }
    
    public void dec(String key) {
        if (!keyToNodeMap.containsKey(key))
            return;

        Node nxt = keyToNodeMap.get(key);
        int updatedValue = nxt.value - 1;
        if (nxt.pre.value != updatedValue && updatedValue != 0)
            insertNode(nxt.pre, new Node(updatedValue));

        Node cur = nxt.pre;
        nxt.keys.remove(key);
        if (nxt.keys.isEmpty())
            removeNode(nxt);
        keyToNodeMap.remove(key);
        if (updatedValue == 0)
            return;
        
        cur.keys.add(key);
        keyToNodeMap.put(key, cur);
    }
    
    public String getMaxKey() {
        if (head.nxt == head)
            return "";
        return head.pre.keys.iterator().next();
    }
    
    public String getMinKey() {
        
        if (head.nxt == head)
            return "";
        return head.nxt.keys.iterator().next();
    }
    
    private void removeNode(Node node)  {
        node.nxt.pre = node.pre;
        node.pre.nxt = node.nxt;
        node.pre = null;
        node.nxt = null;
    }
    
    private void insertNode(Node pre, Node cur) {
        cur.nxt = pre.nxt;
        cur.pre = pre;
        pre.nxt.pre = cur;
        pre.nxt = cur;
    }
}

class Node  {
    Set<String> keys;
    int value;
    Node pre;
    Node nxt;
    
    public Node(int value)  {
        keys = new HashSet<>();
        this.value = value;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */