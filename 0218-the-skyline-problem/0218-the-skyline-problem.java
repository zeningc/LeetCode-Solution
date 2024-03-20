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
            node.val = Math.max(node.val, val);
            return;
        }
        
        int mid = getMid(node.lo, node.hi);
        
        if (hi <= mid)  {
            update(node.left, lo, hi, val);
        }
        else if (lo > mid)  {
            update(node.right, lo, hi, val);
        }
        else    {
            update(node.left, lo, mid, val);
            update(node.right, mid + 1, hi, val);
        }
        
        node.val = Math.max(node.left.val, node.right.val);
    }
    
    public void update(int lo, int hi, int val)   {
        update(root, lo, hi, val);
    }
    
    private int query(Node node, int lo, int hi)   {
        if (lo == node.lo && hi == node.hi)
            return node.val;
        
        int mid = getMid(node.lo, node.hi);
        if (hi <= mid)
            return query(node.left, lo, hi);
        else if (lo > mid)
            return query(node.right, lo, hi);
        
        return Math.max(query(node.left, lo, mid), query(node.right, mid + 1, hi));
    }
    
    public int query(int lo, int hi)    {
        return query(root, lo, hi);
    }
    
    public int query(int idx)    {
        return query(root, idx, idx);
    }
}

class Node  {
    int val;
    int lo;
    int hi;
    Node left;
    Node right;
    
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