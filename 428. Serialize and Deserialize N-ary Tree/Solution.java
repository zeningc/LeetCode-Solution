/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    int idx;
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(root.val));
        if (root.children.size() != 0)  {
            sb.append("[");
            for (Node node : root.children) {
                sb.append(serialize(node));
            }
            sb.append("]");
        }
        else
            sb.append(" ");
        return sb.toString();
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        return dfs(data);
    }
    
    Node dfs(String data)   {
        if (idx >= data.length()) {
            return null;
        }
        
        if (data.charAt(idx) == ']')    {
            idx++;
            return null;
        }
        int j = idx;
        int val = 0;
        while (j < data.length() && data.charAt(j) >= '0' && data.charAt(j) <= '9') {
            val = val * 10 + data.charAt(j) - '0';
            j++;
        }
        
        Node node = new Node(val);
        node.children = new LinkedList<>();
        idx = j;
        if (idx < data.length() && data.charAt(idx) == ' ')
            idx++;
        if (idx < data.length() && data.charAt(j) == '[')  {
            idx++;
            while (true)    {
                Node child = dfs(data);
                if (child != null)
                    node.children.add(child);
                else
                    break;
            }
        }
        
        return node;
        
    }
}
