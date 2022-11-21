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
    int[] arr = new int[100001];
    int p = 0;
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        dfs(root);
        int n = p;
        List<List<Integer>> ans = new LinkedList<>();
        for (int q :queries)    {
            int lo = 0;
            int hi = n - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid] >= q)  {
                    hi = mid - 1;
                }
                else    {
                    lo = mid + 1;
                }
            }
            
            if (lo == n)    {
                ans.add(List.of(arr[n - 1], -1));
                continue;
            }
            
            
            if (arr[lo] == q)   {
                ans.add(List.of(q, q));
                continue;
            }
            
            if (lo == 0)    {
                ans.add(List.of(-1, arr[0]));
                continue;
            }
            
            ans.add(List.of(arr[lo - 1], arr[lo]));
        }
        
        return ans;
    }
    
    void dfs(TreeNode root) {
        if (root == null)
            return;
        dfs(root.left);
        arr[p++] = root.val;
        dfs(root.right);
    }
   
}