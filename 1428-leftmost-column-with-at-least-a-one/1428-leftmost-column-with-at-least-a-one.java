/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0);
        int n = dimensions.get(1);
        
        int x = m - 1;
        int y = n - 1;
        int ans = n;
        while (x >= 0 && y >= 0)    {
            if (binaryMatrix.get(x, y) == 1)    {
                ans = Math.min(ans, y);
                y--;
                continue;
            }
            x--;
        }
        
        return ans == n ? -1 : ans;
    }
}