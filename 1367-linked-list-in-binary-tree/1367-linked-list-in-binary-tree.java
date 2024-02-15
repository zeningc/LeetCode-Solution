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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        StringBuilder sb = new StringBuilder();
        List<Integer> headList = new ArrayList<>();
        while (head != null)    {
            headList.add(head.val);
            head = head.next;
        }
        return dfs(headList, new ArrayList<>(), root);
    }
    
    boolean dfs(List<Integer> head, List<Integer> curList, TreeNode root)   {
        if (root == null)
            return head.size() == 0;
        curList.add(root.val);
        if (isSame(head, curList))
            return true;
        if (dfs(head, curList, root.left))
            return true;
        if (dfs(head, curList, root.right))
            return true;
        curList.remove(curList.size() - 1);
        return false;
    }
    
    boolean isSame(List<Integer> a, List<Integer> b)    {
        if (a.size() > b.size())
            return false;
        for (int i = 0; i < a.size(); i++)
            if (a.get(i) != b.get(b.size() - a.size() + i))
                return false;
        return true;
    }
}