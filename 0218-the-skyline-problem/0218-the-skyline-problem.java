class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Map<Integer, Integer> idxToId = new HashMap<>();
        int cnt = 0;
        idxToId.put(0, 0);
        cnt++;
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] building : buildings)    {
            set.add(building[0]);
            set.add(building[1] - 1);
            set.add(building[1]);
        }
        TreeSet<Integer> resultSet = new TreeSet<>();
        for (int[] building : buildings)    {
            resultSet.add(building[0]);
            resultSet.add(building[1]);
        }
        for (int s : set)   {
            idxToId.put(s, cnt);
            cnt++;
        }
        
        SegmentTree st = new SegmentTree(cnt);
        
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] building : buildings)
            st.update(idxToId.get(building[0]), idxToId.get(building[1] - 1), building[2]);

        
        int pre = -1;
        for (int idx : resultSet)   {
            int cur = st.query(idxToId.get(idx), idxToId.get(idx));
            if (pre != cur)
                ans.add(List.of(idx, cur));
            pre = cur;
        }
        
        return ans;
    }
}

class SegmentTree   {
    Node root;
    
    public SegmentTree(int n)    {
        root = buildTree(0, n - 1);
    }
    
    private Node buildTree(int lo, int hi)  {
        if (lo == hi)
            return new Node(lo, hi, 0);
        
        int mid = lo + (hi - lo) / 2;
        Node left = buildTree(lo, mid);
        Node right = buildTree(mid + 1, hi);
        
        Node cur = new Node(lo, hi, 0);
        cur.left = left;
        cur.right = right;
        
        return cur;
    }
    
    public int query(int lo, int hi)   {
        return query(root, lo, hi);
    }
    
    private int query(Node node, int lo, int hi)    {
        if (node.lo == lo && node.hi == hi)
            return node.val;
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        int ans = 0;
        if (lo <= mid)
            ans = Math.max(ans, query(node.left, lo, Math.min(hi, mid)));
        if (hi > mid)
            ans = Math.max(ans, query(node.right, Math.max(mid + 1, lo), hi));
        
        return ans;
    }
    
    public void update(int lo, int hi, int val) {
        update(root, lo, hi, val);
    }
    
    private void update(Node node, int lo, int hi, int val)   {
        if (node.lo == lo && node.hi == hi && lo == hi) {
            node.val = Math.max(node.val, val);
            return;
        }
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        if (hi > mid)
            update(node.right, Math.max(mid + 1, lo), hi, val);
        node.val = Math.max(node.left.val, node.right.val);
    }
}

class Node  {
    Node left;
    Node right;
    int lo;
    int hi;
    int val;
    
    public Node(int lo, int hi, int val)    {
        this.lo = lo;
        this.hi = hi;
        this.val = val;
    }
}