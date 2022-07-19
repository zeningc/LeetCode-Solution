class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        TreeNode p1 = root;
        List<Integer> ans = new LinkedList<>();
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
                addToAns(ans, p1.left);
            }
            
            p1 = p1.right;
        }
        addToAns(ans, root);
        return ans;
    }
    
    void addToAns(List<Integer> ans, TreeNode p1)  {
        int pos = ans.size();
        
        while (p1 != null)    {
            ans.add(pos, p1.val);
            p1 = p1.right;
        }
    }
}
