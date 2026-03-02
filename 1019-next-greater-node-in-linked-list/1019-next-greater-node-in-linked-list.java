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
    public int[] nextLargerNodes(ListNode head) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        Map<ListNode, Integer> m = new HashMap<>();
        int idx = 0;
        int n = 0;
        ListNode node = head;

        while (node != null)    {
            n++;
            node = node.next;
        }
        int[] ans = new int[n];
        while (head != null)    {
            m.put(head, idx++);
            while (!pq.isEmpty() && pq.peek().val < head.val)   {
                ListNode pop = pq.poll();
                int popIdx = m.get(pop);
                ans[popIdx] = head.val;
            }
            pq.offer(head);
            head = head.next;
        }

        return ans;
    }
}