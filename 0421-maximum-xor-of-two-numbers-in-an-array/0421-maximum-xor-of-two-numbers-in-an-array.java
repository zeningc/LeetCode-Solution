class Solution {
    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();
        for (int num : nums)
            root.insert(num);
        int ans = 0;
        for (int num : nums)    {
            ans = Math.max(ans, root.findMaxXor(num) ^ num);
        }
        return ans;
    }
}

class TrieNode  {
    int n;
    TrieNode[] children;
    
    public TrieNode()   {
        n = -1;
        children = new TrieNode[2];
    }
    
    public void insert(int num)    {
        TrieNode node = this;
        for (int i = 31; i >= 0; i--)   {
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (node.children[b] == null)
                node.children[b] = new TrieNode();
            node = node.children[b];
        }
        node.n = num;
    }
    
    public int findMaxXor(int num)  {
        TrieNode node = this;
        for (int i = 31; i >= 0; i--)   {
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (node.children[1 - b] != null)
                node = node.children[1 - b];
            else if (node.children[b] != null)
                node = node.children[b];
            else
                return -1;
        }
        
        return node.n;
    }
}