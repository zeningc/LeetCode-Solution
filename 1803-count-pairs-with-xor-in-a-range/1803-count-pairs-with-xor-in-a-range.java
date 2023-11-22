class Solution {
    public int countPairs(int[] nums, int low, int high) {
        return countPairsLessThan(nums, high + 1) - countPairsLessThan(nums, low);
    }
    
    public int countPairsLessThan(int[] nums, int limit)   {
        TrieNode root = new TrieNode();
        int cnt = 0;
        for (int num : nums)    {
            cnt += TrieNode.countPairInTrie(root, num, limit, 0);
            TrieNode.insert(root, num);
        }
        
        return cnt;
    }
}

class TrieNode  {
    int cnt;
    TrieNode[] children;
    
    public TrieNode()   {
        cnt = 0;
        children = new TrieNode[2];
    }
    
    
    public static void insert(TrieNode root, int num)  {
        TrieNode node = root;
        for (int i = 31; i >= 0; i--)   {
            node.cnt++;
            int b = (num & (1 << i)) == 0 ? 0 : 1;
            if (node.children[b] == null)
                node.children[b] = new TrieNode();
            node = node.children[b];
        }
        node.cnt++;
    }
    
    
    public static int countPairInTrie(TrieNode root, int num, int limit, int depth)  {
        if (root == null)
            return 0;
        if (depth == 32)
            return 0;
        int cnt = 0;
        int b = (limit & (1 << (31 - depth))) == 0 ? 0 : 1;
        int c = (num & (1 << (31 - depth))) == 0 ? 0 : 1;
        
        if (b == 0 && c == 0) {
            cnt += countPairInTrie(root.children[0], num, limit, depth + 1);
        }
        else if (b == 0 && c == 1)  {
            cnt += countPairInTrie(root.children[1], num, limit, depth + 1);
        }
        else if (b == 1 && c == 0)  {
            cnt += root.children[0] == null ? 0: root.children[0].cnt;
            cnt += countPairInTrie(root.children[1], num, limit, depth + 1);
        }
        else if (b == 1 && c == 1)  {
            cnt += countPairInTrie(root.children[0], num, limit, depth + 1);
            cnt += root.children[1] == null ? 0: root.children[1].cnt;
        }
        
        return cnt;
    }
}