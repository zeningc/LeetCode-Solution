class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] f : flowers)   {
            set.add(f[0]);
            set.add(f[1]);
            set.add(f[1] + 1);
        }
        
        int t = 1;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int f : set)   {
            map.put(f, t++);
        }
        
        Node root = buildTree(0, t + 2);
        
        for (int[] f : flowers)   {
            updateTree(root, map.get(f[0]), 1);
            updateTree(root, map.get(f[1]) + 1, -1);
        }
        int[] ans = new int[persons.length];
        int idx = 0;
        for (int p : persons)   {
            Integer lower = map.floorKey(p);
            if (lower == null)  {
                ans[idx++] = 0;
                continue;
            }
            ans[idx++] = queryRange(root, 0, map.get(lower));
        }
        return ans;
    }
    
    private void updateTree(Node root, int index, int val)   {
        if (root.start == index && root.end == index)   {
            root.sum += val;
            return;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (index <= mid)   {
            updateTree(root.left, index, val);
        }
        else    {
            updateTree(root.right, index, val);
        }
        root.sum = root.left.sum + root.right.sum;
    }
    
    private int queryRange(Node root, int start, int end)   {
        if (root.start == start && root.end == end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        if (end <= mid) {
            return queryRange(root.left, start, end);
        }
        else if (start > mid)   {
            return queryRange(root.right, start, end);
        }
        return queryRange(root.left, start, mid) + queryRange(root.right, mid + 1, end);
    }
    
    private Node buildTree(int start, int end)   {
        if (start == end)   {
            return new Node(start, end, 0, null, null);
        }
        int mid = start + (end - start) / 2;
        Node left = buildTree(start, mid);
        Node right = buildTree(mid + 1, end);
        return new Node(start, end, left.sum + right.sum, left, right);
    }
}

class Node  {
    
    int start;
    int end;
    Node left;
    Node right;
    int sum;
    
    public Node(int start, int end, int sum, Node left, Node right) {
        this.start = start;
        this.end = end;
        this.sum = sum;
        this.left = left;
        this.right = right;
    }
}
