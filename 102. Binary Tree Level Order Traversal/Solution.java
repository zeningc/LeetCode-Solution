class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        Deque<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new LinkedList<>();
        
        q.offer(root);
        
        while (!q.isEmpty())    {
            int size = q.size();
            List<Integer> level = new LinkedList<>();
            while (size-- > 0) {
                root = q.poll();
                level.add(root.val);
                if (root.left != null)
                    q.offer(root.left);
                if (root.right != null)
                    q.offer(root.right);
            }
            ans.add(level);
        }
        
        return ans;
    }
}
