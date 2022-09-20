/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Map<Node, Node> copyMap = new HashMap<>();
        Node root = head;
        while (root != null)    {
            copyMap.put(root, new Node(root.val));
            root = root.next;
        }
        
        root = head;
        while (root != null)    {
            if (root.next != null)
                copyMap.get(root).next = copyMap.get(root.next);
            if (root.random != null)
                copyMap.get(root).random = copyMap.get(root.random);
            root = root.next;
        }
        
        return copyMap.get(head);
    }
}