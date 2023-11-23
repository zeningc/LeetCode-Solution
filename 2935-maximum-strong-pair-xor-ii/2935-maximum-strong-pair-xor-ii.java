class Solution {
    public int maximumStrongPairXor(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        TrieNode root = new TrieNode();
        int ans = 0;
        for (int l = 0, r = 0; l < n; l++)    {
            while (r < nums.length && nums[r] - nums[l] <= nums[l])
            {
                root.insert(nums[r]);
                r++;
            }
            root.delete(nums[l]);
            if (l < r)
                ans = Math.max(ans, nums[l] ^ root.findXorMax(nums[l]));
        }
        
        return ans;
    }
}

class TrieNode  {
    int n;
    int cnt;
    TrieNode[] children;
    
    public TrieNode()   {
        n = -1;
        cnt = 0;
        children = new TrieNode[2];
    }
    
    public void insert(int num)    {
        TrieNode node = this;
        for (int i = 31; i >= 0; i--)   {
            node.cnt++;
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (node.children[b] == null)
                node.children[b] = new TrieNode();
            node = node.children[b];
        }
        node.cnt++;
        node.n = num;
    }
    
    public void delete(int num) {
        TrieNode node = this;
        for (int i = 31; i >= 0; i--)   {
            node.cnt--;
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (node.children[b] == null || node.children[b].cnt <= 0)
                return;
            node = node.children[b];
        }
        node.cnt--;
    }
    
    public int findXorMax(int num)  {
        TrieNode node = this;
        for (int i = 31; i >= 0; i--)   {
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (node.children[1 - b] != null && node.children[1 - b].cnt > 0)
                node = node.children[1 - b];
            else if (node.children[b] != null && node.children[b].cnt > 0)
                node = node.children[b];
            else
                return -1;
        }
        
        return node.n;
    }
}