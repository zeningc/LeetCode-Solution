/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode p = head;
        ListNode q = head;
        boolean init = true;
        while (p != null && q != null && (p != q || init))  {
            init = false;
            p = p.next;
            q = q.next;
            if (q != null)
                q = q.next;
        }
        if (p == null || q == null)
            return null;
        q = head;
        while (p != q)  {
            p = p.next;
            q = q.next;
        }

        return p;
    }
}