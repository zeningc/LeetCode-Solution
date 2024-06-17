class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Map<Integer, Integer> posToId = new HashMap<>();
        Map<Integer, Integer> idToPos = new HashMap<>();
        int id = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] building : buildings)    {
            set.add(building[0]);
            set.add(building[1]);
        }
        
        for (int val : set) {
            posToId.put(val, id);
            idToPos.put(id, val);
            id++;
        }
        
        SegmentTree segTree = new SegmentTree(0, id, 0);
        for (int[] building : buildings)
            segTree.update(posToId.get(building[0]), posToId.get(building[1]) - 1, building[2]);
        
        
        int pre = -1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < id; i++)    {
            int cur = segTree.query(i);
            if (cur != pre)
                ans.add(List.of(idToPos.get(i), cur));
            pre = cur;
        }
        
        return ans;
    }
}


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
            node.val = Math.max(node.val, val);
            node.lazyTag = Math.max(node.lazyTag, val);
            node.needUpdate = true;
            return;
        }

        lazyUpdate(node);

        int mid = node.lo + (node.hi - node.lo) / 2;

        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        if (hi > mid)
            update(node.right, Math.max(lo, mid + 1), hi, val);

        node.val = Math.max(node.left.val, node.right.val);
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
            ret = Math.max(ret, query(node.left, lo, Math.min(hi, mid)));
        if (hi > mid)
            ret = Math.max(ret, query(node.right, Math.max(lo, mid + 1), hi));
        return ret;
    }

    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }

    public int query(int i)    {
        return query(i, i);
    }

    private void lazyUpdate(TreeNode node)  {
        if (node.needUpdate)    {
            node.left.val = Math.max(node.lazyTag, node.left.val);
            node.right.val = Math.max(node.lazyTag, node.right.val);
            node.left.lazyTag = node.left.needUpdate ? Math.max(node.left.lazyTag, node.lazyTag) : node.lazyTag;
            node.right.lazyTag = node.right.needUpdate ? Math.max(node.right.lazyTag, node.lazyTag) : node.lazyTag;
            node.left.needUpdate = true;
            node.right.needUpdate = true;
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