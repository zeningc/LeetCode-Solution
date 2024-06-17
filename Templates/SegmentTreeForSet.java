class SegmentTree   {
    TreeNode root;
    
    public SegmentTree(int lo, int hi, int val)  {
        root = buildTree(lo, hi, val);
    }
    
    private TreeNode buildTree(int lo, int hi, int val)  {
        if (lo == hi)
            return new TreeNode(lo, hi, val);
        int mid = lo + (hi - lo) / 2;
        return new TreeNode(lo, hi, val, buildTree(lo, mid, val), buildTree(mid + 1, hi, val));
    }
    
    private void update(TreeNode node, int lo, int hi, int val) {
        if (node.lo == node.hi && node.hi == hi)   {
            node.val = (node.hi - node.lo + 1) * val;
            node.lazyTag = val;
            return;
        }
        
        lazyUpdate(node);
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        
        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        if (hi > mid)
            update(node.right, Math.max(lo, mid + 1), hi, val);
        
        node.val = node.left.val + node.right.val;
    }
    
    public void update(int lo, int hi, int val)   {
        update(root, lo, hi, val);
    }
    
    public void update(int idx, int val)    {
        update(idx, idx, val);
    }
    
    private int query(TreeNode node, int lo, int hi)   {
        if (lo == node.lo && hi == node.hi)
            return node.val;
        lazyUpdate(node);
        int mid = node.lo + (node.hi - node.lo) / 2;
        int ret = 0;
        if (lo <= mid)
            ret += query(node.left, lo, Math.min(hi, mid));
        if (hi > mid)
            ret += query(node.right, Math.max(lo, mid + 1), hi);
        return ret;
    }

    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    private void lazyUpdate(TreeNode node)  {
        if (node.needUpdate)    {
            node.left.val = (node.left.hi - node.left.lo + 1) * node.lazyTag;
            node.right.val = (node.right.hi - node.right.lo + 1) * node.lazyTag;
            node.left.lazyTag += node.lazyTag;
            node.right.lazyTag += node.lazyTag;
            node.lazyTag = 0;
            node.needUpdate = false;
        }
    }
}

class TreeNode  {
    int val;
    int lo;
    int hi;
    TreeNode left;
    TreeNode right;
    int lazyTag;
    boolean needUpdate;
    
    public TreeNode(int lo, int hi, int val)    {
        this.lo = lo;
        this.hi = hi;
        this.val = val;
    }
    
    public TreeNode(int lo, int hi, int val, TreeNode left, TreeNode right) {
        this(lo, hi, val);
        this.left = left;
        this.right = right;
    }
}