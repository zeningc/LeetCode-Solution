class BrowserHistory {
    int idx;
    int max;
    Node curr;
    Map<Integer, Node> idxToNodeMap;
    public BrowserHistory(String homepage) {
        curr = new Node(homepage);
        idxToNodeMap = new HashMap<>();
        idx = 0;
        max = 0;
        idxToNodeMap.put(idx, curr);
    }
    
    public void visit(String url) {
        Node next = new Node(url);
        curr.next= next;
        next.prev = curr;
        idx++;
        max = idx;
        idxToNodeMap.put(idx, next);
        curr = next;
    }
    
    public String back(int steps) {
        curr = idxToNodeMap.get(Math.max(0, idx - steps));
        idx = Math.max(0, idx - steps);
        return curr.url;
    }
    
    public String forward(int steps) {
        curr = idxToNodeMap.get(Math.min(max, idx + steps));
        idx = Math.min(max, idx + steps);
        return curr.url;
    }
}

class Node  {
    String url;
    Node prev;
    Node next;
    
    public Node(String url) {
        this.url = url;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */