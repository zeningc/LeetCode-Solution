class Solution {
    Map<Integer, Long> subTreeSumMap;
    Map<Integer, List<Integer>> edgesMap;
    int[] values;
    long ans;
    TrieNode root;
    public long maxXor(int n, int[][] edges, int[] values) {
        subTreeSumMap  = new HashMap<>();
        edgesMap = new HashMap<>();
        this.values = values;
        ans = 0;
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            edgesMap.computeIfAbsent(u, x -> new LinkedList<>()).add(v);
            edgesMap.computeIfAbsent(v, x -> new LinkedList<>()).add(u);
        }
        root = new TrieNode();
        calcSubTreeSum(0, -1);
        Map<Integer, Long> subTreeSumMap = this.subTreeSumMap;
        findMaxSumXor(0, -1);
        return ans;
    }
    
    private void findMaxSumXor(int u, int p)    {
        long sum = subTreeSumMap.get(u);
        root.delete(sum);
        for (int v : edgesMap.get(u))   {
            if (v == p)
                continue;
            findMaxSumXor(v, u);
        }
        long match = root.findMaxXor(sum);
        if (match != -1)
            ans = Math.max(ans, sum ^ match);
    }
    
    private long calcSubTreeSum(int u, int p)   {
        long sum = values[u];
        for (int v : edgesMap.get(u))   {
            if (v == p)
                continue;
            sum += calcSubTreeSum(v, u);
        }
        
        subTreeSumMap.put(u, sum);
        root.insert(sum);
        return sum;
    }
    
    
    
}

class TrieNode  {
    long n;
    int cnt;
    TrieNode[] children;
    
    
    public TrieNode()   {
        n = -1;
        cnt = 0;
        children = new TrieNode[2];
    }
    
    public void insert(long num)    {
        TrieNode node = this;
        for (int i = 63; i >= 0; i--)   {
            node.cnt++;
            int b = (num & (1L << i)) != 0 ? 1 : 0;
            if (node.children[b] == null)
                node.children[b] = new TrieNode();
            node = node.children[b];
        }
        node.cnt++;
        node.n = num;
    }
    
    public void delete(long num) {
        TrieNode node = this;
        for (int i = 63; i >= 0; i--)   {
            node.cnt--;
            int b = (num & (1L << i)) != 0 ? 1 : 0;
            if (node.children[b] == null)
                return;
            node = node.children[b];
        }
        node.cnt--;
    }
    
    public long findMaxXor(long num)   {
        TrieNode node = this;
        for (int i = 63; i >= 0; i--)   {
            int bit = (num & (1L << i)) != 0 ? 1 : 0;
            if (node.children[1 - bit] != null && node.children[1 - bit].cnt > 0)
                node = node.children[1 - bit];
            else if (node.children[bit] != null && node.children[bit].cnt > 0)
                node = node.children[bit];
            else
                return -1;
        }
        
        return node.n;
    }
}