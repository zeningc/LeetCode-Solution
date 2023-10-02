/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    Map<Integer, TreeNode> valToNodeMap;
    Map<Integer, PriorityQueue<Integer>> levelHeightMap;
    Map<TreeNode, Integer> nodeToLevelMap;
    Map<TreeNode, Integer> nodeToHeightMap;
    public int[] treeQueries(TreeNode root, int[] queries)
    {
        int n = queries.length;
        valToNodeMap = new HashMap<>();
        levelHeightMap = new HashMap<>();
        nodeToLevelMap = new HashMap<>();
        nodeToHeightMap = new HashMap<>();
        dfs(root, 0);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
        {
            int nodeVal = queries[i];
            TreeNode delNode = valToNodeMap.get(nodeVal);
            int level = nodeToLevelMap.get(delNode);
            int delHeight = nodeToHeightMap.get(delNode);
            int h = -1;
            if (levelHeightMap.get(level).peek() == delHeight)
            {
                levelHeightMap.get(level).poll();
                if (!levelHeightMap.get(level).isEmpty())
                    h = levelHeightMap.get(level).peek();
                levelHeightMap.get(level).offer(delHeight);
            }
            else
            {
                h = levelHeightMap.get(level).peek();
            }
            
            if (h == -1)
                ans[i] = level - 1;
            else
                ans[i] = level + h - 1;
        }
        
        return ans;
    }
    
    int dfs(TreeNode u, int level)
    {
        if (u == null)
            return 0;
        nodeToLevelMap.put(u, level);
        valToNodeMap.put(u.val, u);
        int left = dfs(u.left, level + 1);
        int right = dfs(u.right, level + 1);
        nodeToHeightMap.put(u, Math.max(left, right) + 1);
        levelHeightMap.computeIfAbsent(level, x -> new PriorityQueue<>((a, b) -> b - a)).add(Math.max(left, right) + 1);
        return Math.max(left, right) + 1;
    }
}