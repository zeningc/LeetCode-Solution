class Solution {
    public int[] resultArray(int[] nums) {
        Map<Integer, Integer> numToId = new HashMap<>();
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        int cnt = 0;
        for (int num : sortedNums)    {
            if (numToId.containsKey(num))
                continue;
            numToId.put(num, cnt);
            cnt++;
        }
        
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        SegmentTree st1 = new SegmentTree(0, cnt, 0);
        SegmentTree st2 = new SegmentTree(0, cnt, 0);
        
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        st1.update(numToId.get(nums[0]), numToId.get(nums[0]), 1);
        st2.update(numToId.get(nums[1]), numToId.get(nums[1]), 1);
        for (int i = 2; i < nums.length; i++)   {
            int curId = numToId.get(nums[i]);
            int cnt1 = st1.query(curId + 1, cnt);
            int cnt2 = st2.query(curId + 1, cnt);
            if (cnt1 > cnt2)    {
                arr1.add(nums[i]);
                st1.update(curId, curId, 1);
            }
            else if (cnt2 > cnt1)   {
                arr2.add(nums[i]);
                st2.update(curId, curId, 1);
            }
            else if (cnt1 == cnt2)  {
                if (arr1.size() > arr2.size())  {
                    arr2.add(nums[i]);
                    st2.update(curId, curId, 1);
                }
                else    {
                    arr1.add(nums[i]);
                    st1.update(curId, curId, 1);
                }
            }
        }
        
        int[] ret = new int[nums.length];
        for (int i = 0; i < arr1.size(); i++)
            ret[i] = arr1.get(i);
        
        for (int i = 0; i < arr2.size(); i++)
            ret[i + arr1.size()] = arr2.get(i);
        
        return ret;
    }
}

class SegmentTree   {
    Node root;
    
    public SegmentTree(int lo, int hi, int val)    {
        
        this.root = buildTree(lo, hi, val);
    }
    
    private Node buildTree(int lo, int hi, int val)  {
        if (lo == hi)
            return new Node(lo, hi, val);
        int mid = lo + (hi - lo) / 2;
        Node cur = new Node(lo, hi, val);
        cur.left = buildTree(lo, mid, val);
        cur.right = buildTree(mid + 1, hi, val);
        cur.val = cur.left.val + cur.right.val;
        return cur;
    }
    
    public void update(int lo, int hi, int val)  {
        update(root, lo, hi, val);
    }
    
    private void update(Node node, int lo, int hi, int val)  {
        if (node.lo == lo && node.hi == hi) {
            node.lazyTag += val;
            node.val += val;
            return;
        }
        
        updateLazyTag(node);
        
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            update(node.left, lo, Math.min(hi, mid), val);
        if (hi > mid)
            update(node.right, Math.max(mid + 1, lo), hi, val);
        node.val = node.left.val + node.right.val;
    }
    
    public int query(int lo, int hi)  {
        return query(root, lo, hi);
    }
    
    private int query(Node node, int lo, int hi)  {
        if (node.lo == lo && node.hi == hi) {
            return node.val;
        }
        
        updateLazyTag(node);
        int ret = 0;
        int mid = node.lo + (node.hi - node.lo) / 2;
        if (lo <= mid)
            ret += query(node.left, lo, Math.min(hi, mid));
        if (hi > mid)
            ret += query(node.right, Math.max(mid + 1, lo), hi);
        node.val = node.left.val + node.right.val;
        return ret;
    }
    
    private void updateLazyTag(Node node)    {
        if (node.lazyTag != 0 && node.left != null && node.right != null)  {
            node.left.val += node.lazyTag * (node.left.hi - node.left.lo + 1);
            node.right.val += node.lazyTag * (node.right.hi - node.right.lo + 1);
            node.left.lazyTag += node.lazyTag;
            node.right.lazyTag += node.lazyTag;
            node.lazyTag = 0;
        }
    }
}

class Node  {
    int val;
    Node left;
    Node right;
    int lo;
    int hi;
    int lazyTag;
    
    public Node(int lo, int hi, int val)    {
        this.lo = lo;
        this.hi = hi;
        this.val = val;
    }
}