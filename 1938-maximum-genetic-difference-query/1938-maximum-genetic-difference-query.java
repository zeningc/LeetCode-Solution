class Solution {
    public int[] maxGeneticDifference(int[] parents, int[][] queries) {
        int[] ans = new int[queries.length];
        Map<Integer, List<Integer>> nodeToIdx = new HashMap<>();
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 0; i < parents.length; i++)
            edges.computeIfAbsent(parents[i], x -> new LinkedList<>()).add(i);
        
        for (int i = 0; i < queries.length; i++)
            nodeToIdx.computeIfAbsent(queries[i][0], x -> new LinkedList<>()).add(i);
        
        int rootNode = -1;
        TrieNode root = new TrieNode();
        for (int i = 0; i < parents.length; i++)
            if (parents[i] == -1)
                rootNode = i;
        
        dfs(nodeToIdx, edges, root, queries, ans, rootNode);
        
        return ans;
    }
    
    private void dfs(Map<Integer, List<Integer>> nodeToIdx, Map<Integer, List<Integer>> edges, TrieNode trie, int[][] queries, int[] ans, int u)   {
        TrieNode.insert(trie, u);
        assignAnswer(nodeToIdx, trie, queries, ans, u);
        if (edges.containsKey(u))   {
            for (int v : edges.get(u))  {
                dfs(nodeToIdx, edges, trie, queries, ans, v);
            }
        }
        TrieNode.delete(trie, u, 0);
    }
    
    private void assignAnswer(Map<Integer, List<Integer>> nodeToIdx, TrieNode root, int[][] queries, int[] ans, int u)   {
        if (!nodeToIdx.containsKey(u))
            return;
        for (int idx : nodeToIdx.get(u))    {
            int val = queries[idx][1];
            int match = TrieNode.findXorMax(root, val);
            ans[idx] = val ^ match;
        }
    }
}

class TrieNode  {
    TrieNode[] children;
    int n;
    int cnt = 0;
    
    public TrieNode()   {
        children = new TrieNode[2];
        n = -1;
        cnt = 0;
    }
    
    public static void insert(TrieNode root, int num)  {
        for (int i = 31; i >= 0; i--)   {
            root.cnt++;
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (root.children[b] == null)
                root.children[b] = new TrieNode();
            root = root.children[b];
        }
        root.cnt++;
        root.n = num;
    }
    
    public static int findXorMax(TrieNode root, int num)    {
        for (int i = 31; i >= 0; i--)   {
            int b = (num & (1 << i)) != 0 ? 1 : 0;
            if (root.children[1 - b] != null)
                root = root.children[1 - b];
            else if (root.children[b] != null)
                root = root.children[b];
            else
                return -1;
        }
        return root.n;
    }
    
    public static void delete(TrieNode root, int num, int depth)  {
        if (root.n != -1)   {
            root.n = -1;
            root.cnt--;
            return;
        }
        int b = (num & (1 << (31 - depth))) != 0 ? 1 : 0;
        if (root.children[b] == null)
            return;
        delete(root.children[b], num, depth + 1);
        if (root.children[b].cnt == 0)
            root.children[b] = null;
        root.cnt--;
    }
    
    // public static TrieNode copy(TrieNode root)  {
    //     if (root == null)
    //         return null;
    //     TrieNode copyNode = new TrieNode();
    //     copyNode.children[0] = copy(root.children[0]);
    //     copyNode.children[1] = copy(root.children[1]);
    //     copyNode.n = root.n;
    //     return copyNode;
    // }
    
    // public static void getAllNum(Set<Integer> set, TrieNode root)    {
    //     if (root == null)
    //         return;
    //     if (root.n != -1)   {
    //         set.add(root.n);
    //         return;
    //     }
    //     getAllNum(set, root.children[0]);
    //     getAllNum(set, root.children[1]);
    //     return;
    // }
}