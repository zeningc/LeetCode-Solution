/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        ListNode dummy1 = new ListNode(-1, head);
        ListNode slow = dummy1;
        ListNode fast = head;
        
        while (fast != null)    {
            slow = slow.next;
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
        }
        
        ListNode dummy2 = new ListNode(-1, slow.next);
        slow.next = null;
        
        dummy2.next = reverse(dummy2.next);
        
        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;
        ListNode p = dummy1.next;
        ListNode q = dummy2.next;
        while (p != null)  {
            ptr.next = p;
            p = p.next;
            ptr = ptr.next;
            if (q != null)  {
                ptr.next = q;
                q = q.next;
                ptr = ptr.next;
            }
        }
        
        head = dummy.next;
    }
    
    ListNode reverse(ListNode node) {
        if (node == null || node.next == null)
            return node;
        
        ListNode root = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return root;
    }
}
