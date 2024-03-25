class Solution {
    public int[] resultArray(int[] nums) {
        Map<Integer, Integer> numToId = new HashMap<>();
        int id = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums)
            set.add(num);
        for (int num : set)
            numToId.put(num, id++);
        Node arr1Root = segTreeCreate(0, id);
        Node arr2Root = segTreeCreate(0, id);
        
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]);
        segTreeUpdate(arr1Root, numToId.get(nums[0]), numToId.get(nums[0]), 1);
        arr2.add(nums[1]);
        segTreeUpdate(arr2Root, numToId.get(nums[1]), numToId.get(nums[1]), 1);
    
        for (int i = 2; i < nums.length; i++)   {
            int curId = numToId.get(nums[i]);
            boolean addToArr1 = false;
            int gt1 = segTreeQuery(arr1Root, curId + 1, id);
            int gt2 = segTreeQuery(arr2Root, curId + 1, id);
            if (gt1 > gt2)  {
                addToArr1 = true;
            }
            else if (gt1 < gt2) {
                addToArr1 = false;
            }
            else    {
                if (arr1.size() < arr2.size())  {
                    addToArr1 = true;
                }
                else if (arr1.size() > arr2.size()) {
                    addToArr1 = false;
                }
                else    {
                    addToArr1 = true;
                }
            }
            
            if (addToArr1)  {
                arr1.add(nums[i]);
                segTreeUpdate(arr1Root, numToId.get(nums[i]), numToId.get(nums[i]), 1);
            }
            else    {
                arr2.add(nums[i]);
                segTreeUpdate(arr2Root, numToId.get(nums[i]), numToId.get(nums[i]), 1);
            }
        }
        
        arr1.addAll(arr2);
        int[] ans = new int[arr1.size()];
        for (int i = 0; i < arr1.size(); i++)
            ans[i] = arr1.get(i);
        return ans;
    }
    
    int segTreeQuery(Node root, int low, int high)   {
        if (root.low == low && root.high == high)  {
            return root.val;
        }
        
        int mid = root.low + (root.high - root.low) / 2;
        if (high <= mid)    {
            return segTreeQuery(root.left, low, high);
        }
        
        if (low > mid)    {
            return segTreeQuery(root.right, low, high);
        }
        
        return segTreeQuery(root.left, low, mid) + segTreeQuery(root.right, mid + 1, high);
    }
    
    void segTreeUpdate(Node root, int low, int high, int val)  {
        if (low == high && root.low == root.high && low == root.low)    {
            root.val += val;
            return;
        }
        int mid = root.low + (root.high - root.low) / 2;
        if (high <= mid)    {
            segTreeUpdate(root.left, low, high, val);
        }
        else if (low > mid)  {
            segTreeUpdate(root.right, low, high, val);
        }
        else    {
            segTreeUpdate(root.left, low, mid, val);
            segTreeUpdate(root.right, mid + 1, high, val);
        }
        root.val = root.left.val + root.right.val;
        return;
    }
    
    Node segTreeCreate(int low, int high)   {
        Node root = new Node(null, null, low, high, 0);
        if (low == high)    {
            return root;
        }
        int mid = low + (high - low) / 2;
        root.left = segTreeCreate(low, mid);
        root.right = segTreeCreate(mid + 1, high);
        return root;
    }
}

class Node  {
    int low;
    int high;
    Node left;
    Node right;
    int val;
    
    public Node(Node left, Node right, int low, int high, int val) {
        this.left = left;
        this.right = right;
        this.low = low;
        this.high = high;
        this.val = val;
    }
}