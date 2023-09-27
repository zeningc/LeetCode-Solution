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
    public int minimumFlips(TreeNode root, boolean result) {
        return dfs(root)[result ? 1 : 0];
    }
    
    int[] dfs(TreeNode root)    {
        if (root == null)
            return new int[] {0, 0};
        if (root.left == null && root.right == null)
            return new int[] {root.val == 0 ? 0 : 1, root.val == 1 ? 0 : 1};
        if (root.left == null)  {
            int[] right = dfs(root.right);
            return new int[] {right[1], right[0]};
        }
        if (root.right == null)  {
            int[] left = dfs(root.left);
            return new int[] {left[1], left[0]};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int[] ret = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                boolean a = i == 0 ? false : true;
                boolean b = j == 0 ? false : true;
                boolean c = false;
                int result = -1;
                switch(root.val)    {
                    case 2:
                        c = a || b;
                        break;
                    case 3:
                        c = a && b;
                        break;
                    case 4:
                        c = a ^ b;
                    default:
                        break;
                }
                result = c ? 1 : 0;
                ret[result] = Math.min(ret[result], left[i] + right[j]);
            }
        }
        
        return ret;
    }
}