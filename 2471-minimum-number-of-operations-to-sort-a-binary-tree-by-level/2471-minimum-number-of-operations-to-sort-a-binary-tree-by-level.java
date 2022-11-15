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
    public int minimumOperations(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int ans = 0;
        int[] l = new int[100000];
        int[] l1 = new int[100000];
        Map<Integer, Integer> val2Idx = new HashMap<>();
        while (!q.isEmpty())    {
            int size = q.size();
            for (int i = 0; i < size; i++)  {
                TreeNode node = q.poll();
                l[i] = node.val;
                l1[i] = node.val;
                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }
            Arrays.sort(l, 0, size);
            for (int i = 0; i < size; i++)
                val2Idx.put(l[i], i);
            for (int i = 0; i < size; i++)  {
                while (l1[i] != l[i])   {
                    int target = val2Idx.get(l1[i]);
                    int temp = l1[target];
                    l1[target] = l1[i];
                    l1[i] = temp;
                    ans++;
                }
            }
        }
        
        return ans;
    }
}