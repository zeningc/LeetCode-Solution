class Solution {
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        
        map.put(node, new Node(node.val));
        
        for (Node v : node.neighbors)   {
            if (!map.containsKey(v))
                cloneGraph(v);
            map.get(node).neighbors.add(map.get(v));
        }
        
        return map.get(node);
    }
}
