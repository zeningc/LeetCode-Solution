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
    ListNode[] lists;
    public ListNode mergeKLists(ListNode[] lists) {
        this.lists = lists;
        return mergeList(0, lists.length - 1);
    }
    
    ListNode mergeList(int lo, int hi)  {
        if (lo > hi)
            return null;
        if (lo == hi)
            return lists[lo];
        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        int mid = lo + (hi - lo) / 2;
        ListNode p = mergeList(lo, mid);
        ListNode q = mergeList(mid + 1, hi);
        
        while (p != null && q != null)  {
            if (p.val < q.val)  {
                curr.next = p;
                curr = p;
                p = p.next;
            }
            else    {
                curr.next = q;
                curr = q;
                q = q.next;
            }
        }
        
        while (p != null)   {
            curr.next = p;
            curr = p;
            p = p.next;
        }
        
        while (q != null)   {
            curr.next = q;
            curr = q;
            q = q.next;
        }
        
        return dummy.next;
    }
}
