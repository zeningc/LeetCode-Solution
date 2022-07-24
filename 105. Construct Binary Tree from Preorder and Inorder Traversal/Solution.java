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
    int index;
    int n;
    int[] preorder;
    int[] inorder;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.n=preorder.length;
        this.preorder=preorder;
        this.inorder=inorder;
        this.index=0;
        return dfs(0, n-1);
    }
    
    private TreeNode dfs(int left, int right)  {
        if(left>right||index>n-1)  {
            return null;
        }
        for(int mid=left;mid<=right;mid++) {
            if(inorder[mid]==preorder[index])   {
                TreeNode root = new TreeNode(preorder[index]);
                index++;
                root.left=dfs(left, mid-1);
                root.right=dfs(mid+1, right);
                return root;
            }
        }
        return null;
    }
    
    
}