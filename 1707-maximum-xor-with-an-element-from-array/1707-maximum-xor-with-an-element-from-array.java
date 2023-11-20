class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Map<Pair<Integer, Integer>, List<Integer>> queryToIdxMap = new HashMap<>();
        TrieNode root = new TrieNode(-1);
        Arrays.sort(nums);
        int p = 0;
        int n = nums.length;
        int qLen = queries.length;
        for (int i = 0; i < qLen; i++) {
            int x = queries[i][0];
            int m = queries[i][1];
            queryToIdxMap.computeIfAbsent(new Pair<Integer, Integer>(x, m),  y -> new LinkedList<>()).add(i);
        }
        Arrays.sort(queries, (a, b) -> a[1] - b[1]);
        int[] ans = new int[qLen];
        for (int i = 0; i < qLen; i++)  {
            int x = queries[i][0];
            int m = queries[i][1];
            while (p < n && nums[p] <= m)   {
                TrieNode.insert(root, nums[p]);
                p++;
            }
            int j = TrieNode.search(root, x);
            int curAns = j == -1 ? -1 : x ^ j;
            for (int idx : queryToIdxMap.get(new Pair<Integer, Integer>(x, m)))
                ans[idx] = curAns;
        }
        
        return ans;
    }
}

class TrieNode {
    int bit;
    TrieNode[] children;
    int num;
    
    public TrieNode(int bit)    {
        this.bit = bit;
        children = new TrieNode[2];
        num = -1;
    }
    
    public static void insert(TrieNode root, int y) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--)    {
            int bit = (y & (1 << i)) == 0 ? 0 : 1;
            if (node.children[bit] == null)
                node.children[bit] = new TrieNode(bit);
            node = node.children[bit];
        }
        node.num = y;
    }
    
    public static int search(TrieNode root, int x) {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--)    {
            int bit = (x & (1 << i)) == 0 ? 0 : 1;
            if (node.children[1 - bit] != null)
                node = node.children[1 - bit];
            else if (node.children[bit] != null)
                node = node.children[bit];
            else
                return -1;
        }
        return node.num;
    }
}