class Solution {
    public int sumCounts(int[] nums) {
        Map<Integer, Integer> lastSeenMap = new HashMap<>();
        int ans = 0;
        SegmentTree st = new SegmentTree(0, nums.length - 1);
        long lastSquare = 0;
        for (int i = 0; i < nums.length; i++)   {
            int k = lastSeenMap.getOrDefault(nums[i], -1);
            long curSquare = lastSquare;
            curSquare += 2 * st.query(k + 1, i - 1) + 1 + i - 1 - k;
            ans = (int)((curSquare + ans) % 1000000007);
            lastSquare = curSquare;
            st.update(k + 1, i, 1);
            lastSeenMap.put(nums[i], i);
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
            node.val += val * (node.hi - node.lo + 1);
            node.lazyTag += val;
            return;
        }
        lazyUpdate(node);
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        if (hi > mid)
            update(node.right, Math.max(mid + 1, lo), hi, val);

        node.val = node.left.val + node.right.val;
    }
    
    void lazyUpdate(Node node)  {
        if (node.lazyTag != 0 && node.left != null && node.right != null)   {
            node.left.val += node.lazyTag * (node.left.hi - node.left.lo + 1);
            node.right.val += node.lazyTag * (node.right.hi - node.right.lo + 1);
            node.left.lazyTag += node.lazyTag;
            node.right.lazyTag += node.lazyTag;
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
            ret += query(node.left, lo, Math.min(hi, mid));

        if (hi > mid)   
            ret += query(node.right, Math.max(mid + 1, lo), hi);

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