class NumArray {
    SegmentTree segTree;
    public NumArray(int[] nums) {
        segTree = new SegmentTree(0, nums.length - 1, 0);
        for (int i = 0; i < nums.length; i++)    {
            segTree.update(i, nums[i]);
        }
    }
    
    public void update(int index, int val) {
        segTree.update(index, val);
    }
    
    public int sumRange(int left, int right) {
        return segTree.query(left, right);
    }
}

class SegmentTree   {
    Node root;
    public SegmentTree(int lo, int hi, int initVal)  {
        root = buildTree(lo, hi, initVal);
    }
    
    private Node buildTree(int lo, int hi, int initVal)  {
        if (lo == hi)   {
            return new Node(lo, hi, initVal);
        }
        
        int mid = lo + (hi - lo) / 2;
        Node left = buildTree(lo, mid, initVal);
        Node right = buildTree(mid + 1, hi, initVal);
        return new Node(lo, hi, initVal, left, right);
    }
    
    public void update(int idx, int val)    {
        update(root, idx, val);
    }
    
    private void update(Node node, int idx, int val)   {
        if (idx == node.lo && idx == node.hi)   {
            node.sum = val;
            return;
        }
        int mid = node.lo + (node.hi - node.lo) / 2;
        
        if (idx <= mid)
            update(node.left, idx, val);
        else
            update(node.right, idx, val);
        node.sum = node.left.sum + node.right.sum;
    }
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    private int query(Node node, int lo, int hi)    {
        if (lo == node.lo && hi == node.hi)    {
            return node.sum;
        }
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (hi <= mid)  {
            return query(node.left, lo, hi);
        }
        else if (lo > mid)  {
            return query(node.right, lo, hi);
        }
        return query(node.left, lo, mid) + query(node.right, mid + 1, hi);
    }
}

class Node  {
    int sum;
    int lo;
    int hi;
    Node left;
    Node right;
    
    public Node(int lo, int hi, int sum)   {
        this.lo = lo;
        this.hi = hi;
        this.sum = sum;
    }
    
    public Node(int lo, int hi, int sum, Node left, Node right)   {
        this.lo = lo;
        this.hi = hi;
        this.left = left;
        this.right = right;
        this.sum = sum;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */