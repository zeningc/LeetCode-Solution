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
    int n;
    public List<TreeNode> generateTrees(int n) {
       this.n = n;
        return dfs(1, n);
    }
    
    List<TreeNode> dfs(int lo, int hi)  {
        if (hi < lo)    {
            List<TreeNode> t = new LinkedList<>();
            t.add(null);
            return t;
        }
            
        if (lo == hi)
            return List.of(new TreeNode(lo));
        
        List<TreeNode> ans = new LinkedList<>();
        List<TreeNode> left;
        List<TreeNode> right;
        for (int i = lo; i <= hi; i++)  {
            left = dfs(lo, i - 1);
            right = dfs(i + 1, hi);
            for (TreeNode l : left) {
                for (TreeNode r : right)    {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ans.add(root);
                }
            }
            
        }
        
        return ans;
    }
}
