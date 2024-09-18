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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<List<Integer>> ansList = new ArrayList<>();
        TreeNode p1 = root;
        while (p1 != null)  {
            TreeNode p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1)
                    p2 = p2.right;
                if (p2.right != p1) {
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                }
                
                p2.right = null;
                ansList.add(getList(p1.left));
            }
            p1 = p1.right;
        }
        ansList.add(getList(root));
        
        List<Integer> ret = new ArrayList<>();
        for (List<Integer> subList : ansList)
            ret.addAll(subList);
        
        return ret;
    }
    
    List<Integer> getList(TreeNode root)    {
        List<Integer> ret = new LinkedList<>();
        while (root != null)    {
            ret.add(0, root.val);
            root = root.right;
        }
        return ret;
    }
}