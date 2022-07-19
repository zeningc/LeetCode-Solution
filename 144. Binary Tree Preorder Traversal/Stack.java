class Solution {
    List<Integer> ans;
    public List<Integer> preorderTraversal(TreeNode root) {
        ans = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null)    {
            while (node!= null)   {
                ans.add(node.val);
                stack.push(node);
                node = node.left;
            }
            
            node = stack.pop();
            node = node.right;
        }
        
        return ans;
    }
}
