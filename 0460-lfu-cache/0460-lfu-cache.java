class LFUCache {
    int capacity;
    FreqNode root;
    Map<Integer, Node> keyToNodeMap;
    Map<Integer, FreqNode> freqToRootMap;
    int cnt;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        root = new FreqNode(-1);
        root.nxt = root;
        root.pre = root;
        keyToNodeMap = new HashMap<>();
        freqToRootMap = new HashMap<>();
        cnt = 0;
    }
    
    public int get(int key) {
        if (!keyToNodeMap.containsKey(key))
            return -1;
        addFreqToKey(key);
        return keyToNodeMap.get(key).val;
    }
    
    public void put(int key, int value) {
        if (!keyToNodeMap.containsKey(key) && cnt + 1 > capacity)
            deleteLFU();
        else if (!keyToNodeMap.containsKey(key))
            cnt++;
        
        if (!keyToNodeMap.containsKey(key)) {
            Node node = new Node(key, value, 1);
            keyToNodeMap.put(key, node);
            if (!freqToRootMap.containsKey(1))  {
                FreqNode nxtFreqNode = new FreqNode(1);
                freqToRootMap.put(1, nxtFreqNode);
                addNode(root, nxtFreqNode);
            }
            FreqNode nxtFreqNode = freqToRootMap.get(1);
            addNode(nxtFreqNode.root, node);
        }
        else    {
            Node node = keyToNodeMap.get(key);
            node.val = value;
            addFreqToKey(key);
        }
    }
    
    void deleteLFU()    {
        FreqNode minFreqNode = root.nxt;
        Node nodeToRemove = minFreqNode.root.pre;
        removeNode(nodeToRemove);
        keyToNodeMap.remove(nodeToRemove.key);
        
        if (minFreqNode.root.nxt == minFreqNode.root)   {
            removeNode(minFreqNode);
            freqToRootMap.remove(minFreqNode.freq);
        }
    }
    
    void addFreqToKey(int key)  {
        Node keyNode = keyToNodeMap.get(key);
        int curFreq = keyNode.freq;
        keyNode.freq++;
        int nxtFreq = keyNode.freq;
        FreqNode curFreqNode = freqToRootMap.get(curFreq);
        removeNode(keyNode);
        FreqNode nxtFreqNode;
        if (!freqToRootMap.containsKey(nxtFreq))    {
            nxtFreqNode = new FreqNode(nxtFreq);
            addNode(curFreqNode, nxtFreqNode);
            freqToRootMap.put(nxtFreq, nxtFreqNode);
        }
        else    {
            nxtFreqNode = freqToRootMap.get(nxtFreq);
        }
        
        addNode(nxtFreqNode.root, keyNode);
        
        if (curFreqNode.root.nxt == curFreqNode.root)   {
            removeNode(curFreqNode);
            freqToRootMap.remove(curFreqNode.freq);
        }
    }
    
    
    void removeTail(Node root)  {
        removeNode(root.pre);
    }
    
    void addNode(Node root, Node node)    {
        node.nxt = root.nxt;
        node.pre = root;
        root.nxt.pre = node;
        root.nxt = node;
    }
    
    void addNode(FreqNode root, FreqNode node)    {
        node.nxt = root.nxt;
        node.pre = root;
        root.nxt.pre = node;
        root.nxt = node;
    }
    
    void removeNode(Node node)  {
        node.nxt.pre = node.pre;
        node.pre.nxt = node.nxt;
        node.pre = null;
        node.nxt = null;
    }
    
    void removeNode(FreqNode node)  {
        node.nxt.pre = node.pre;
        node.pre.nxt = node.nxt;
        node.pre = null;
        node.nxt = null;
    }
    
}

class FreqNode  {
    FreqNode pre;
    FreqNode nxt;
    int freq;
    Node root;
    public FreqNode(int freq)   {
        this.freq = freq;
        root = new Node(-1, -1, -1);
        root.nxt = root;
        root.pre = root;
    }
}

class Node  {
    Node pre;
    Node nxt;
    int key;
    int val;
    int freq;
    
    public Node(int key, int val, int freq)   {
        this.key = key;
        this.val = val;
        this.freq = freq;
    }
    
    
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */