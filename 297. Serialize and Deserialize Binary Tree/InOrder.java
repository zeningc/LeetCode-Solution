public class Codec {
    Deque<String> q;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "#";
        return String.valueOf(root.val) + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.split(",");
        if (split.length == 1)  {
            if (split[0].equals("#"))
                return null;
            return new TreeNode(Integer.parseInt(split[0]));
        }
        q = new LinkedList<>();
        for (String s : split)
            q.offer(s);
        return dfs();
    }
    
    TreeNode dfs()   {
        if (q.isEmpty())
            return null;
        String val = q.poll();
        if (val.equals("#"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = dfs();
        root.right = dfs();
        return root;
    }
    
    
}