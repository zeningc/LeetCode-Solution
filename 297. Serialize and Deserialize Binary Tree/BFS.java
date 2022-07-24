public class Codec {
    StringBuilder sb = new StringBuilder();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty())    {
            TreeNode node = q.poll();
            if (node == null)   {
                sb.append("#");
            }
            else    {
                sb.append(String.valueOf(node.val));
                q.offer(node.left);
                q.offer(node.right);
            }
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        if (split.length == 1)  {
            if (split[0].equals("#"))
                return null;
            return new TreeNode(Integer.parseInt(split[0]));
        }
        int n = split.length;
        TreeNode[] treeArr = new TreeNode[n];
        int p = 0;
        treeArr[0] = new TreeNode(Integer.parseInt(split[0]));
        int q = 1;
        while (q < n)   {
            while (split[p].equals("#"))
                p++;
            TreeNode parent = treeArr[p];
            TreeNode left = split[q].equals("#") ? null : new TreeNode(Integer.parseInt(split[q]));
            TreeNode right = split[q + 1].equals("#") ? null : new TreeNode(Integer.parseInt(split[q + 1]));
            treeArr[q] = left;
            treeArr[q + 1] = right;
            parent.left = left;
            parent.right = right;
            q += 2;
            p++;
        }
        
        return treeArr[0];
    }
}