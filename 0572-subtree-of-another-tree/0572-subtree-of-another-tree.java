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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        List<Integer> rootSeq = convert(root);
        List<Integer> subRootSeq = convert(subRoot);
        int targetLen = subRootSeq.size();
        List<Integer> l = subRootSeq;
        l.add(Integer.MAX_VALUE);
        l.addAll(rootSeq);
        int[] pi = new int[l.size()];
        for (int i = 1; i < l.size(); i++)  {
            int j = pi[i - 1];
            while (j > 0 && !Objects.equals(l.get(i), l.get(j)))
                j = pi[j - 1];
            if (Objects.equals(l.get(i), l.get(j)))
                j++;
            pi[i] = j;
            if (i > targetLen && j == targetLen)
                return true;
        }
        
        return false;
    }
    
    List<Integer> convert(TreeNode root)    {
        if (root == null)
            return List.of(Integer.MIN_VALUE);
        List<Integer> l = new ArrayList<>();
        l.add(root.val);
        l.addAll(convert(root.left));
        l.addAll(convert(root.right));
        return l;
    }
}