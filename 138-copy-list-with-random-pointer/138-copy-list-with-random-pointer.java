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
        Node node = head;
        
        while (node != null)    {
            Node newNode = new Node(node.val);
            Node next = node.next;
            node.next = newNode;
            newNode.next = next;
            node = next;
        }
        
        node = head;
        Node newHead = head.next;
        while (node != null)    {
            if (node.random != null)
                node.next.random = node.random.next;
            node = node.next.next;
        }
        
        node = head;
        
        while (node != null)    {
            Node next = node.next.next;
            if (next != null)
                node.next.next = next.next;
            else
                node.next.next = null;
            node.next = next;
            node = next;
        }
        
        return newHead;
    }
}