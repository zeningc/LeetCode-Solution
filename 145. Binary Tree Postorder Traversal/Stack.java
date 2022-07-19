class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        Set<TreeNode> vis = new HashSet<>();
        List<Integer> ans = new LinkedList<>();
        TreeNode node = root;
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || node != null)    {
            while (node != null)    {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (vis.contains(node)){
                ans.add(node.val);
                node = null;
                continue;
            }
            vis.add(node);
            stack.push(node);
            node = node.right;
        }
        
        return ans;
    }
}
