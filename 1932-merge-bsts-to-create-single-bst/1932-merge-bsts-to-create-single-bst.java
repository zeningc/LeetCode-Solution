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
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> valToRoot = new HashMap<>();
        Set<Integer> childValSet = new HashSet<>();
        for (TreeNode tree : trees) {
            valToRoot.put(tree.val, tree);
            if (tree.left != null)
                childValSet.add(tree.left.val);
            if (tree.right != null)
                childValSet.add(tree.right.val);
        }
        TreeNode root = null;
        for (TreeNode tree : trees) {
            if (childValSet.contains(tree.val))
                continue;
            if (root != null)
                return null;
            root = tree;
        }
        
        if (dfs(valToRoot, root, Integer.MIN_VALUE, Integer.MAX_VALUE) && valToRoot.size() == 1)
            return root;
        
        return null;
    }
    
    boolean dfs(Map<Integer, TreeNode> valToRoot, TreeNode root, int min, int max)  {
        if (root == null)
            return true;
        if (root.val <= min || root.val >= max)
            return false;
        if (root.left != null)  {
            if (valToRoot.containsKey(root.left.val))   {
                root.left = valToRoot.get(root.left.val);
                valToRoot.remove(root.left.val);
            }
            if (!dfs(valToRoot, root.left, min, root.val))
                return false;
        }
        
        if (root.right != null)  {
            if (valToRoot.containsKey(root.right.val))   {
                root.right = valToRoot.get(root.right.val);
                valToRoot.remove(root.right.val);
            }
            if(!dfs(valToRoot, root.right, root.val, max))
                return false;
        }
        
        return true;
    }
}