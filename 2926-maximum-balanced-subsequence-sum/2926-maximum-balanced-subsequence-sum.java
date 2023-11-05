class Solution {
    public long maxBalancedSubsequenceSum(int[] nums) {
        long n = nums.length;
        Map<Long, Long> valToIdx = new HashMap<>();
        Map<Long, Long> idxToVal = new HashMap<>();
        TreeSet<Long> set = new TreeSet<>();
        long lo = Long.MAX_VALUE;
        long hi = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            lo = Math.min(lo, (long)i - nums[i]);
            hi = Math.max(hi, (long)i - nums[i]);
            set.add((long)i - nums[i]);
        }
        long cnt = 0;
        for (long element : set)    {
            valToIdx.put(element, cnt);
            idxToVal.put(cnt, element);
            cnt++;
        }
        Node root = segTreeCreate(valToIdx.get(lo), valToIdx.get(hi));
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            long pre = segTreeQuery(root, valToIdx.get((long)i - nums[i]), valToIdx.get(hi));
            pre = Math.max(pre, 0L);
            segTreeUpdate(root, valToIdx.get((long)i - nums[i]), valToIdx.get((long)i - nums[i]), pre + nums[i]);
            ans = Math.max(pre + nums[i], ans);
        }
        
        return ans;
    }
    
    long segTreeQuery(Node root, long low, long high)   {
        if (root.low == low && root.high == high)  {
            return root.val;
        }
        
        long mid = root.low + (root.high - root.low) / 2;
        if (high <= mid)    {
            return segTreeQuery(root.left, low, high);
        }
        
        if (low > mid)    {
            return segTreeQuery(root.right, low, high);
        }
        
        return Math.max(segTreeQuery(root.left, low, mid), segTreeQuery(root.right, mid + 1, high));
    }
    
    void segTreeUpdate(Node root, long low, long high, long val)  {
        if (low == high && root.low == root.high && low == root.low)    {
            root.val = Math.max(val, root.val);
            return;
        }
        long mid = root.low + (root.high - root.low) / 2;
        if (high <= mid)    {
            segTreeUpdate(root.left, low, high, val);
            root.val = Math.max(root.val, val);
            return;
        }
        if (low > mid)  {
            segTreeUpdate(root.right, low, high, val);
            root.val = Math.max(root.val, val);
            return;
        }
        segTreeUpdate(root.left, low, mid, val);
        segTreeUpdate(root.right, mid + 1, high, val);
        root.val = Math.max(root.val, Math.max(root.left.val, root.right.val));
        return;
    }
    
    Node segTreeCreate(long low, long high)   {
        Node root = new Node(null, null, low, high, 0);
        if (low == high)    {
            return root;
        }
        long mid = low + (high - low) / 2;
        root.left = segTreeCreate(low, mid);
        root.right = segTreeCreate(mid + 1, high);
        return root;
    }
}

class Node  {
    long low;
    long high;
    Node left;
    Node right;
    long val;
    
    public Node(Node left, Node right, long low, long high, long val) {
        this.left = left;
        this.right = right;
        this.low = low;
        this.high = high;
        this.val = val;
    }
}