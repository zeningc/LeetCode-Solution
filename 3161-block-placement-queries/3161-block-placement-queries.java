class Solution {
    public List<Boolean> getResults(int[][] queries) {
        TreeSet<Integer> set = new TreeSet<>();
        SegmentTree st = new SegmentTree(0, queries.length * 3);
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries) {
            int type = q[0];
            int x = q[1];
            if (type == 1)  {
                Integer pre = set.lower(x);
                if (pre == null)
                    pre = 0;
                st.update(x, x, x - pre);
                Integer nxt = set.higher(x);
                if (nxt != null)
                    st.update(nxt, nxt, nxt - x);
                set.add(x);
                continue;
            }
            int sz = q[2];
            if (st.query(0, x) >= sz)   {
                ans.add(true);
                continue;
            }
            
            if (st.query(x, x) == 0 && x - Optional.ofNullable(set.lower(x)).orElse(0) >= sz)    {
                ans.add(true);
                continue;
            }
            
            ans.add(false);
        }
        
        return ans;
    }
}

class SegmentTree   {
    Node root;
    
    public SegmentTree(int lo, int hi)    {
        root = buildTree(lo, hi);
    }

    private Node buildTree(int lo, int hi)   {
        if (lo == hi)
            return new Node(lo, hi);
        
        int mid = lo + (hi - lo) / 2;
        Node left = buildTree(lo, mid);
        Node right = buildTree(mid + 1, hi);
        
        Node cur = new Node(lo, hi);
        cur.left = left;
        cur.right = right;
        
        return cur;
    }
    
    public void update(int lo, int hi, long val)  {
        update(root, lo, hi, val);
    }

    public void update(Node node, int lo, int hi, long val)  {
        if (node.lo == lo && node.hi == hi) {
            node.val = val;
            node.lazyTag = val;
            return;
        }
        lazyUpdate(node);
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        if (hi > mid)
            update(node.right, Math.max(mid + 1, lo), hi, val);

        node.val = Math.max(node.left.val, node.right.val);
    }
    
    void lazyUpdate(Node node)  {
        if (node.lazyTag != 0 && node.left != null && node.right != null)   {
            node.left.val = node.lazyTag;
            node.right.val = node.lazyTag;
            node.left.lazyTag = node.lazyTag;
            node.right.lazyTag = node.lazyTag;
            node.lazyTag = 0;
        }
    }
    
    public long query(int lo, int hi)   {
        if (lo > hi)
            return 0;
        return query(root, lo, hi);
    }
    
    private long query(Node node, int lo, int hi) {
        if (node.lo == lo && node.hi == hi)
            return node.val;
        
        lazyUpdate(node);
        int mid = node.lo + (node.hi - node.lo) / 2;
        long ret = 0;

        if (lo <= mid)
            ret = Math.max(ret, query(node.left, lo, Math.min(hi, mid)));

        if (hi > mid)   
            ret = Math.max(ret, query(node.right, Math.max(mid + 1, lo), hi));

        return ret;
    }
}

class Node  {
    long val;
    Node left;
    Node right;
    int lo;
    int hi;
    long lazyTag;
    
    public Node(int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.val = 0;
        this.lazyTag = 0;
    }
}