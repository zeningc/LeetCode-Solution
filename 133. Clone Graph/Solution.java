/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        Map<Node, Node> map = new HashMap<>();
        Deque<Node> q = new LinkedList<>();
        q.add(node);
        map.put(node, new Node(node.val));
        while (!q.isEmpty())    {
            Node u = q.poll();
            
            if (u.neighbors == null)
                continue;
            
            for (Node v : u.neighbors)  {
                if (!map.containsKey(v)) {
                    map.put(v, new Node(v.val));
                    q.offer(v);
                }
                map.get(u).neighbors.add(map.get(v));
            }
        }
        
        return map.get(node);
    }
}
