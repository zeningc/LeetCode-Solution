class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new LinkedList<>();
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
            }
            ans.add(p1.val);
            p1 = p1.right;
        }
        
        return ans;
    }
}
