class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (p.val > q.val)  {
            TreeNode t = p;
            p = q;
            q = t;
        }
        
        if (p.val > root.val)  {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        
        if (q.val < root.val)  {
            return lowestCommonAncestor(root.left, p, q);
        }
        
        return root;
    }
}