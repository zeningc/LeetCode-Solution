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
    Map<TreeNode, TreeNode> parentMap;
    TreeNode root;
    public int amountOfTime(TreeNode root, int start) {
        this.root = root;
        parentMap = new HashMap<>();
        dfs(root, null);
        TreeNode startNode = dfs2(root, start);
        Deque<TreeNode> q = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        
        q.offer(startNode);
        int step = -1;
        while (!q.isEmpty())    {
            int size = q.size();
            step++;
            while (size-- > 0)  {
                TreeNode u = q.poll();
                if (set.contains(u))
                    continue;
                set.add(u);
                if (u.left != null && !set.contains(u.left))   {
                    q.offer(u.left);
                }
                
                if (u.right != null && !set.contains(u.right))   {
                    q.offer(u.right);
                }
                
                if (parentMap.containsKey(u) && !set.contains(parentMap.get(u)))  {
                    q.offer(parentMap.get(u));
                }
            }
        }
        
        
        return step;
    }
    
    void dfs(TreeNode node, TreeNode parent) {
        if (node == null)
            return;
        if (node != root)
            parentMap.put(node, parent);
        dfs(node.left, node);
        dfs(node.right, node);
    }
    
    TreeNode dfs2(TreeNode root, int start)    {
        if (root == null)
            return null;
        
        if (root.val == start)  {
            return root;
        }
        
        TreeNode left = dfs2(root.left, start);
        if (left != null)
            return left;
        TreeNode right = dfs2(root.right, start);
        if (right != null)
            return right;
        
        return null;
    }
}