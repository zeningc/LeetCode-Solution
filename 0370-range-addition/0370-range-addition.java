class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] ans = new int[length];
        SegmentTree st = new SegmentTree(0, length - 1, 0);
        for (int[] u : updates) {
            st.update(u[0], u[1], u[2]);
        }
        
        for (int i = 0; i < length; i++)
            ans[i] = st.query(i, i);
        
        return ans;
    }
}

class SegmentTree   {
    TreeNode root;
    
    public SegmentTree(int lo, int hi, int val)    {
        root = buildTree(lo, hi, val);
    }
    
    private TreeNode buildTree(int lo, int hi, int val)  {
        if (lo == hi)
            return new TreeNode(lo, hi, val);
        
        int mid = lo + (hi - lo) / 2;
        TreeNode left = buildTree(lo, mid, val);
        TreeNode right = buildTree(mid + 1, hi, val);
        TreeNode cur = new TreeNode(lo, hi, left.val + right.val);
        cur.left = left;
        cur.right = right;
        return cur;
    }
    
    public void update(int lo, int hi, int val)  {
        update(root, lo, hi, val);
    }
    
    private void update(TreeNode node, int lo, int hi, int val)  {
        if (node.lo == lo && node.hi == hi) {
            node.val += (node.hi - node.lo + 1) * val;
            node.lazyTag += val;
            return;
        }

        lazyUpdate(node);
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            update(node.left, lo, Math.min(mid, hi), val);
        if (hi > mid)
            update(node.right, Math.max(lo, mid + 1), hi, val);

        node.val = node.left.val + node.right.val;
    }
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    
    private int query(TreeNode node, int lo, int hi)  {
        if (node.lo == lo && node.hi == hi)
            return node.val;
        
        lazyUpdate(node);
        int ret = 0;
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            ret += query(node.left, lo, Math.min(mid, hi));
        if (hi > mid)
            ret += query(node.right, Math.max(lo, mid + 1), hi);
        
        return ret;
    }
    
    private void lazyUpdate(TreeNode node)    {
        if (node.lazyTag != 0 && node.left != null && node.right != null)    {
            node.left.val += (node.left.hi - node.left.lo + 1) * node.lazyTag;
            node.right.val += (node.right.hi - node.right.lo + 1) * node.lazyTag;
            node.left.lazyTag += node.lazyTag;
            node.right.lazyTag += node.lazyTag;
            node.lazyTag = 0;
        }
    }
}

class TreeNode  {
    int lo;
    int hi;
    TreeNode left;
    TreeNode right;
    int val;
    int lazyTag = 0;
    
    public TreeNode(int lo, int hi, int val)    {
        this.val = val;
        this.lo = lo;
        this.hi = hi;
    }
}