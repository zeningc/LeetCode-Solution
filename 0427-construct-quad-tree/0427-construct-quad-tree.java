/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length - 1, grid[0].length - 1);
    }
    
    Node dfs(int[][] grid, int loX, int loY, int hiX, int hiY)    {
        if (loX == hiX && loY == hiY)   {
            return new Node(grid[loX][loY] == 1, true);
        }
        
        Node topLeft = dfs(grid, loX, loY, (loX + hiX) / 2, (loY + hiY) / 2);
        Node bottomLeft = dfs(grid, (loX + hiX) / 2 + 1, loY, hiX, (loY + hiY) / 2);
        Node topRight = dfs(grid, loX, (loY + hiY) / 2 + 1, (loX + hiX) / 2, hiY);
        Node bottomRight = dfs(grid, (loX + hiX) / 2 + 1, (loY + hiY) / 2 + 1, hiX, hiY);
        
        if (allOne(topLeft, topRight, bottomLeft, bottomRight) || allZero(topLeft, topRight, bottomLeft, bottomRight)) {
            return new Node(topLeft.val, true);
        }
        
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    
    boolean allOne(Node... arr) {
        for (Node a : arr)
            if (!a.isLeaf || !a.val)
                return false;
        return true;
    }
    
    boolean allZero(Node... arr) {
        for (Node a : arr)
            if (!a.isLeaf || a.val)
                return false;
        return true;
    }
}