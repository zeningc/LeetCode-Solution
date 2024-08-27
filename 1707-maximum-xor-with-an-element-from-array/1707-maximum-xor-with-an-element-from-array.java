class Solution {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int n = nums.length;
        int m = queries.length;
        int[] ans = new int[m];
        Arrays.sort(nums);
        List<Integer> idx = new ArrayList<>();
        for (int i = 0; i < m; i++)
            idx.add(i);
        Collections.sort(idx, (a, b) -> queries[a][1] - queries[b][1]);
        Trie root = new Trie('#');
        int k = 0;
        for (int i : idx) {
            while (k < nums.length && nums[k] <= queries[i][1]) {
                Trie node = root;
                for (int j = 31; j >= 0; j--)   {
                    int c = (nums[k] & (1 << j)) == 0 ? 0 : 1;
                    if (node.children[c] == null)
                        node.children[c] = new Trie((char)(c + '0'));
                    node = node.children[c];
                }
                node.num = nums[k];
                k++;
            }
            int x = queries[i][0];
            Trie node = root;
            for (int j = 31; j >= 0; j--)   {
                if (node == null)
                    break;
                int c = (x & (1 << j)) == 0 ? 0 : 1;
                if (c == 1)   {
                    if (node.children[0] != null)   {
                        node = node.children[0];
                        continue;
                    }
                    node = node.children[1];
                    continue;
                }
                if (node.children[1] != null)   {
                    node = node.children[1];
                    continue;
                }
                node = node.children[0];
            }
            ans[i] = node == null ? -1 : node.num ^ x;
        }
        
        return ans;
    }
}
class Trie  {
    Trie[] children;
    char c;
    int num;
    public Trie(char c)   {
        children = new Trie[2];
        this.c = c;
        this.num = -1;
    }
}