class Solution {
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> valToInorderIdxMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        int n = preorder.length;
        valToInorderIdxMap = new HashMap<>();
        for (int i = 0; i < n; i++)
            valToInorderIdxMap.put(inorder[i], i);
        return dfs(0, n - 1, 0, n - 1);
    }
    
    TreeNode dfs(int preLo, int preHi, int inLo, int inHi)  {
        if (preLo > preHi)
            return null;
        if (preLo == preHi)
            return new TreeNode(preorder[preLo]);
        TreeNode root = new TreeNode(preorder[preLo]);
        int inIdx = valToInorderIdxMap.get(preorder[preLo]);
        root.left = dfs(preLo + 1, preLo + inIdx - inLo, inLo, inIdx - 1);
        root.right = dfs(preLo + inIdx - inLo + 1, preHi, inIdx + 1, inHi);
        return root;
    }
}