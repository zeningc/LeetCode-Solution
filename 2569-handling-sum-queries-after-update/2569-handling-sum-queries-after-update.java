class Solution {
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int m = queries.length;
        long sum = 0;
        for (int num : nums2)
            sum += num;
        List<Long> ans = new ArrayList<>();
        SegmentTree st = new SegmentTree(nums1);
        for (int[] q : queries) {
            if (q[0] == 1)  {
                st.update(q[1], q[2], 1);
            }
            else if (q[0] == 2) {
                sum += (long) q[1] * st.query(0, nums1.length - 1);
            }
            else if (q[0] == 3) {
                ans.add(sum);
            }
        }
        
        long[] ansArr = new long[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            ansArr[i] = ans.get(i);
        return ansArr;
    }
}

class SegmentTree   {
    TreeNode root;
    
    public SegmentTree(int[] nums)    {
        root = buildTree(nums);
    }
    
    public TreeNode buildTree(int[] nums)   {
        return buildTree(nums, 0, nums.length - 1);
    }
    
    public TreeNode buildTree(int[] nums, int lo, int hi)   {
        if (lo == hi)
            return new TreeNode(lo, hi, nums[lo]);
        
        TreeNode node = new TreeNode(lo, hi);
        int mid = lo + (hi - lo) / 2;
        TreeNode left = buildTree(nums, lo, mid);
        TreeNode right = buildTree(nums, mid + 1, hi);
        node.left = left;
        node.right = right;
        node.val = left.val + right.val;
        return node;
    }
    
    public void update(int lo, int hi, int val)  {
        update(root, lo, hi, val);
    }
    
    public void update(TreeNode node, int lo, int hi, int val)  {
        if (node.lo == lo && node.hi == hi) {
            if (val % 2 == 1)
                node.val = (node.hi - node.lo + 1 - node.val);
            node.lazyTag += val;
            return;
        }
        
        if (node.lazyTag > 0 && node.left != null && node.right != null)   {
            if (node.lazyTag % 2 == 1)  {
                node.left.val = node.left.hi - node.left.lo + 1 - node.left.val;
                node.right.val = node.right.hi - node.right.lo + 1 - node.right.val;
                node.left.lazyTag += node.lazyTag;
                node.right.lazyTag += node.lazyTag;
            }
            node.lazyTag = 0;
        }
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        
        if (hi > mid)
            update(node.right, Math.max(lo, mid + 1), hi, val);
        
        node.val = node.left.val + node.right.val;
    }
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    public int query(TreeNode node, int lo, int hi) {
        if (node.lo == lo && node.hi == hi) {
            return node.val;
        }
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (node.lazyTag > 0 && node.left != null && node.right != null)   {
            if (node.lazyTag % 2 == 1)  {
                node.left.val = node.left.hi - node.left.lo + 1 - node.left.val;
                node.right.val = node.right.hi - node.right.lo + 1 - node.right.val;
                node.left.lazyTag += node.lazyTag;
                node.right.lazyTag += node.lazyTag;
            }
            node.lazyTag = 0;
        }
        
        int ret = 0;
        if (lo <= mid)
            ret += query(node.left, lo, Math.min(hi, mid));
        if (hi > mid)
            ret += query(node.right, Math.max(lo, mid + 1), hi);
        
        return ret;
    }
}

class TreeNode  {
    TreeNode left;
    TreeNode right;
    int lo;
    int hi;
    int val;
    int lazyTag;
    
    public TreeNode(int lo, int hi)   {
        this.lo = lo;
        this.hi = hi;
    }
    
    public TreeNode(int lo, int hi, int val)   {
        this(lo, hi);
        this.val = val;
    }
}