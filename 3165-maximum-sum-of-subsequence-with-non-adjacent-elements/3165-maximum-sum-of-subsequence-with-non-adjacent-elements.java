class Solution {
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        SegmentTree segTree = new SegmentTree(nums);
        long ans = 0;
        int mod = (int)1e9 + 7;
        for (int[] q : queries) {
            segTree.update(q[0], q[1]);
            ans = (ans + segTree.query()) % mod;
        }
        
        return (int)(ans % mod);
    }
}

class SegmentTree   {
    private static int inf =  (int)1e5 + 1;
    Node root;
    
    public SegmentTree(int[] nums)  {
        root = buildTree(nums, 0, nums.length - 1);
    }
    
    private Node buildTree(int[] nums, int lo, int hi)  {
        if (lo == hi)
            return new Node(lo, hi, 0, -inf, -inf, nums[lo]);
        
        int mid = getMid(lo, hi);
        Node left = buildTree(nums, lo, mid);
        Node right = buildTree(nums, mid + 1, hi);
        long val00 = Math.max(left.val00 + right.val00, Math.max(left.val01 + right.val00, left.val00 + right.val10));
        long val01 = Math.max(left.val00 + right.val01, Math.max(left.val00 + right.val11, left.val01 + right.val01));
        long val10 = Math.max(left.val10 + right.val00, Math.max(left.val10 + right.val10, left.val11 + right.val00));
        long val11 = Math.max(left.val11 + right.val01, Math.max(left.val10 + right.val11, left.val10 + right.val01));
        Node cur = new Node(lo, hi, val00, val01, val10, val11);
        cur.left = left;
        cur.right = right;
        return cur;
    }
    
    private int getMid(int lo, int hi)  {
        return lo + (hi - lo) / 2;
    }
    
    private void update(Node node, int idx, long val) {
        if (node.lo == node.hi && node.hi == idx)   {
            node.val11 = val;
            return;
        }
        
        int mid = getMid(node.lo, node.hi);
        
        if (idx <= mid)  {
            update(node.left, idx, val);
        }
        else if (idx > mid)  {
            update(node.right, idx, val);
        }
        Node left = node.left;
        Node right = node.right;
        node.val00 = Math.max(left.val00 + right.val00, Math.max(left.val01 + right.val00, left.val00 + right.val10));
        node.val01 = Math.max(left.val00 + right.val01, Math.max(left.val00 + right.val11, left.val01 + right.val01));
        node.val10 = Math.max(left.val10 + right.val00, Math.max(left.val10 + right.val10, left.val11 + right.val00));
        node.val11 = Math.max(left.val11 + right.val01, Math.max(left.val10 + right.val11, left.val10 + right.val01));
    }
    
    public void update(int idx, long val)   {
        update(root, idx, val);
    }
    
    public long query()  {
        return Math.max(root.val00, Math.max(root.val01, Math.max(root.val10, root.val11)));
    }
}

class Node  {
    long val00;
    long val01;
    long val10;
    long val11;
    int lo;
    int hi;
    Node left;
    Node right;
    
    public Node(int lo, int hi, long val00, long val01, long val10, long val11)    {
        this.lo = lo;
        this.hi = hi;
        this.val00 = val00;
        this.val01 = val01;
        this.val10 = val10;
        this.val11 = val11;
    }
}