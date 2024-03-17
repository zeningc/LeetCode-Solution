class Solution {
    public int createSortedArray(int[] instructions) {
        int mod = (int)1e9 + 7;
        TreeSet<Integer> set = new TreeSet<>();
        for (int inst : instructions)
            set.add(inst);
        Map<Integer, Integer> numToId = new HashMap<>();
        int id = 0;
        for (int inst : set)
            numToId.put(inst, id++);
        
        SegmentTree tree = new SegmentTree(0, id);
        int ans = 0;
        for (int inst : instructions)   {
            int curId = numToId.get(inst);
            if (curId != 0 && curId != id - 1)
                ans = (ans + Math.min(tree.query(0, curId - 1), tree.query(curId + 1, id))) % mod;
            tree.update(curId, 1);
            
        }
        return ans;
    }
}

class SegmentTree   {
    Node root;
    
    public SegmentTree(int lo, int hi)    {
        this.root = buildTree(lo, hi, 0);
    }
    
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    private int query(Node node, int lo, int hi)    {
        if (node.lo == lo && node.hi == hi)
            return node.val;
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (hi <= mid)
            return query(node.left, lo, hi);
        else if (lo > mid)
            return query(node.right, lo, hi);
        
        return query(node.left, lo, mid) + query(node.right, mid + 1, hi);
    }
    
    public void update(int idx, int val) {
        update(root, idx, val);
    }
    
    private void update(Node node, int idx, int add)    {
        if (node.lo == node.hi && node.lo == idx)   {
            node.val += add;
            return;
        }
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (idx <= mid) {
            update(node.left, idx, add);
        }
        else    {
            update(node.right, idx, add);
        }
        
        node.val = node.left.val + node.right.val;
    }
    
    private Node buildTree(int lo, int hi, int val)   {
        if (lo == hi)
            return new Node(lo, hi, val);
        
        int mid = lo  + (hi - lo) / 2;
        return new Node(buildTree(lo, mid, val), buildTree(mid + 1, hi, val), lo, hi, val);
    }
}

class Node  {
    int val;
    Node left;
    Node right;
    int lo;
    int hi;
    
    public Node(int lo, int hi, int val)    {
        this.lo = lo;
        this.hi = hi;
        this.val = val;
    }
    
    public Node(Node left, Node right, int lo, int hi, int val) {
        this(lo, hi, val);
        this.left = left;
        this.right = right;
    }
}