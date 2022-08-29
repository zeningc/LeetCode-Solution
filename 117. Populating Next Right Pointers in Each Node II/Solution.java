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
    public Node connect(Node root) {
        if (root == null)
            return null;
        Deque<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty())    {
            int size = q.size();
            Node prev = q.poll();
            if (prev.left != null)
                q.offer(prev.left);
            if (prev.right != null)
                q.offer(prev.right);
            while (--size > 0)  {
                Node node = q.poll();
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
                prev.next = node;
                prev = node;
            }
        }
        return root;
    }
}
