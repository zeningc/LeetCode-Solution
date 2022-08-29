/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    Node start;
    Node prev;
    public Node connect(Node root) {
        if (root == null)
            return null;
        Node p = root;
        while (p != null)   {
            handle(p.left);
            handle(p.right);
            p = p.next;
            if (p == null)  {
                p = start;
                start = null;
                prev = null;
            }
        }
        
        return root;
    }
    
    void handle(Node node)  {
        if (node == null)
            return;
        if (start == null)
            start = node;
        if (prev != null)
            prev.next = node;
        prev = node;
    }
}
