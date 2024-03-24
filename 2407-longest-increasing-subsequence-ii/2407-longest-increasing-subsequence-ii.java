class Solution {
    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        Node root = buildTree(0, 100001);
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int maxLen = queryRange(root, Math.max(0, nums[i] - k), nums[i] - 1) + 1;
            updateTree(root, nums[i], maxLen);
            ans = Math.max(ans, maxLen);
        }
        return ans;
    }
    
    private void updateTree(Node root, int index, int val)   {
        if (root.start == index && root.end == index)   {
            root.max = Math.max(root.max, val);
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid)   {
            updateTree(root.left, index, val);
        }
        else    {
            updateTree(root.right, index, val);
        }
        root.max = Math.max(root.left.max, root.right.max);
    }
    
    private int queryRange(Node root, int start, int end)   {
        if (root.start == start && root.end == end) {
            return root.max;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) {
            return queryRange(root.left, start, end);
        }
        else if (start > mid)   {
            return queryRange(root.right, start, end);
        }
        return Math.max(queryRange(root.left, start, mid), queryRange(root.right, mid + 1, end));
    }
    
    private Node buildTree(int start, int end)   {
        if (start == end)   {
            return new Node(start, end, 0, null, null);
        }
        int mid = start + (end - start) / 2;
        Node left = buildTree(start, mid);
        Node right = buildTree(mid + 1, end);
        return new Node(start, end, 0, left, right);
    }
}


class Node  {
    
    int start;
    int end;
    Node left;
    Node right;
    int max;
    
    public Node(int start, int end, int max, Node left, Node right) {
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = left;
        this.right = right;
    }
    
}