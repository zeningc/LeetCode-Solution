class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] position : positions)    {
            set.add(position[0]);
            set.add(position[0] + position[1] - 1);
        }
        int id = 0;
        Map<Integer, Integer> posToId = new HashMap<>();
        for (int pos : set) {
            posToId.put(pos, id++);
        }
        
        SegmentTree segTree = new SegmentTree(0, id, 0);
        for (int[] position : positions)    {
            int left = posToId.get(position[0]);
            int right = posToId.get(position[0] + position[1] - 1);
            int h = segTree.query(left, right) + position[1];
            segTree.update(left, right, h);
            ans.add(segTree.query(0, id));
        }
        
        return ans;
    }
}


class SegmentTree   {
    Node root;
    
    public SegmentTree(int lo, int hi, int val) {
        root = buildTree(lo, hi, val);
    }
    
    private Node buildTree(int lo, int hi, int val) {
        if (lo == hi)
            return new Node(lo, hi, val);
        
        int mid = getMid(lo, hi);
        return new Node(lo, hi, val, buildTree(lo, mid, val), buildTree(mid + 1, hi, val));
    }
    
    private int getMid(int lo, int hi)  {
        return lo + (hi - lo) / 2;
    }
    
    public void update(int lo, int hi, int val) {
        update(root, lo, hi, val);
    }
    
    private void update(Node node, int lo, int hi, int val) {
        if (node.lo == lo && node.hi == lo && lo == hi) {
            node.max = val;
            return;
        }
        
        int mid = getMid(node.lo, node.hi);
        
        if (hi <= mid)  {
            update(node.left, lo, hi, val);
        }
        else if (lo > mid)  {
            update(node.right, lo, hi, val);
        }
        else    {
            update(node.left, lo, mid, val);
            update(node.right, mid + 1, hi, val);
        }
        node.max = Math.max(node.left.max, node.right.max);
    }
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    private int query(Node node, int lo, int hi)    {
        if (node.lo == lo && node.hi == hi)
            return node.max;
        int mid = getMid(node.lo, node.hi);
        if (hi <= mid)
            return query(node.left, lo, hi);
        else if (lo > mid)
            return query(node.right, lo, hi);
        
        return Math.max(query(node.left, lo, mid), query(node.right, mid + 1, hi));
    }
}

class Node  {
    int val;
    int max;
    Node left;
    Node right;
    int lo;
    int hi;
    
    public Node(int lo, int hi, int val)    {
        this.lo = lo;
        this.hi = hi;
        this.val = val;
        this.max = val;
    }
    
    public Node(int lo, int hi, int val, Node left, Node right) {
        this(lo, hi, val);
        this.left = left;
        this.right = right;
    }
}