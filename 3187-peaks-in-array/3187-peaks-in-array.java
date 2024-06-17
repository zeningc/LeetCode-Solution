class Solution {
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        List<Integer> ans = new ArrayList<>();
        SegmentTree st = new SegmentTree(0, nums.length - 1, 0);
        for (int i = 1; i < nums.length - 1; i++)
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
                st.update(i, 1);
        
        for (int[] q : queries) {
            int type = q[0];
            if (type == 1)  {
                int left = q[1] + 1;
                int right = q[2] - 1;
                if (left > right)   {
                    ans.add(0);
                    continue;
                }
                ans.add(st.query(left, right));
                continue;
            }
            int idx = q[1];
            int val = q[2];
            int leftPeak = peakCnt(nums, idx - 1);
            int rightPeak = peakCnt(nums, idx + 1);
            int curPeak = peakCnt(nums, idx);
            nums[idx] = val;
            int leftPeakAfter = peakCnt(nums, idx - 1);
            int rightPeakAfter = peakCnt(nums, idx + 1);
            int curPeakAfter = peakCnt(nums, idx);
            
            if (idx > 0 && leftPeak != leftPeakAfter)
                st.update(idx - 1, leftPeakAfter);
            
            if (idx < nums.length - 1 && rightPeak != rightPeakAfter)
                st.update(idx + 1, rightPeakAfter);
            
            if (curPeak != curPeakAfter)
                st.update(idx, curPeakAfter);
        }
        
        return ans;
    }
    
    int peakCnt(int[] nums, int idx) {
        if (idx <= 0 || idx >= nums.length - 1)
            return 0;
        return nums[idx] > nums[idx - 1] && nums[idx] > nums[idx + 1] ? 1 : 0;
    }
}

class SegmentTree   {
    Node root;
    
    public SegmentTree(int lo, int hi, int val)  {
        root = buildTree(lo, hi, val);
    }
    
    private Node buildTree(int lo, int hi, int val)  {
        if (lo == hi)
            return new Node(lo, hi, val);
        int mid = getMid(lo, hi);
        return new Node(lo, hi, val, buildTree(lo, mid, val), buildTree(mid + 1, hi, val));
    }
    
    private int getMid(int lo, int hi)  {
        return lo + (hi - lo) / 2;
    }
    
    private void update(Node node, int lo, int hi, int val) {
        if (node.lo == node.hi && node.hi == hi)   {
            node.val = (node.hi - node.lo + 1) * val;
            node.lazyTag = val;
            node.needUpdate = true;
            return;
        }
        
        lazyUpdate(node);
        
        int mid = getMid(node.lo, node.hi);
        
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
    
    private int query(Node node, int lo, int hi)   {
        if (lo == node.lo && hi == node.hi)
            return node.val;
        lazyUpdate(node);
        int mid = getMid(node.lo, node.hi);
        int ret = 0;
        if (lo <= mid)
            ret += query(node.left, lo, Math.min(hi, mid));
        if (hi > mid)
            ret += query(node.right, Math.max(lo, mid + 1), hi);
        return ret;
    }
    
    private void lazyUpdate(Node node)  {
        if (node.needUpdate)    {
            node.left.val = (node.left.hi - node.left.lo + 1) * node.lazyTag;
            node.right.val = (node.right.hi - node.right.lo + 1) * node.lazyTag;
            node.left.lazyTag = node.lazyTag;
            node.right.lazyTag = node.lazyTag;
            node.lazyTag = 0;
            node.needUpdate = false;
        }
    }
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
}

class Node  {
    int val;
    int lo;
    int hi;
    Node left;
    Node right;
    int lazyTag;
    boolean needUpdate;
    
    public Node(int lo, int hi, int val)    {
        this.lo = lo;
        this.hi = hi;
        this.val = val;
    }
    
    public Node(int lo, int hi, int val, Node left, Node right) {
        this(lo, hi, val);
        this.left = left;
        this.right = right;
    }
}