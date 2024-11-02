class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Trie root = new Trie(-1);
        
        Arrays.sort(nums);
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < queries.length; i++)
            idx.add(i);
        Collections.sort(idx, (a, b) -> queries[a][1] - queries[b][1]);
        
        int[] ans = new int[queries.length];
        int p = 0;
        for (int q = 0; q < queries.length; q++)    {
            Trie node;
            int curIdx = idx.get(q);
            while (p < nums.length && nums[p] <= queries[curIdx][1])   {
                node = root;
                for (int i = 31; i >= 0; i--)   {
                    int d = (nums[p] & (1 << i)) == 0 ? 0 : 1;
                    if (node.children[d] == null)
                        node.children[d] = new Trie(d);
                    node = node.children[d];
                }
                node.num = nums[p];
                p++;
            }
            
            if (p == 0) {
                ans[curIdx] = -1;
                continue;
            }
            
            node = root;
            for (int i = 31; i >= 0; i--)    {
                int curDigit = (queries[curIdx][0] & (1 << i)) == 0 ? 0 : 1;
                if (curDigit == 0)  {
                    if (node.children[1] != null)   {
                        node = node.children[1];
                        continue;
                    }
                    node = node.children[0];
                    continue;
                }
                
                if (node.children[0] != null)   {
                    node = node.children[0];
                    continue;
                }
                node = node.children[1];
            }
            ans[curIdx] = queries[curIdx][0] ^ node.num;
        }
        return ans;
    }
    
}

class Trie  {
    int d;
    Trie[] children;
    int num;
    
    public Trie(int d)  {
        this.d = d;
        children = new Trie[2];
    }
}