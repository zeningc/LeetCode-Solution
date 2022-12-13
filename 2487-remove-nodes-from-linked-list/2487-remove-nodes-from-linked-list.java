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
    public ListNode removeNodes(ListNode head) {
        ListNode dummy = new ListNode(100001);
        dummy.next = head;
        ListNode[] arr = new ListNode[100001];
        int p = 0;
        ListNode cur = dummy;
        while (cur != null) {
            int val = cur.val;
            int lo = 0;
            int hi = p - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid].val < val) {
                    hi = mid - 1;
                }
                else    {
                    lo = mid + 1;
                }
            }
            arr[lo] = cur;
            if (hi != -1)   {
                arr[hi].next = arr[lo];
            }
            p = lo + 1;
            cur = cur.next;
        }
        return dummy.next;
    }
}