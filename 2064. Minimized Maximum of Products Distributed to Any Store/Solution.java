class Solution {
    int[] quantities;
    int n;
    public int minimizedMaximum(int n, int[] quantities) {
        this.quantities = quantities;
        this.n = n;
        int left = 1;
        int right = (int)1e5 + 1;
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (check(mid)) {
                right = mid - 1;
            }
            else    {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    boolean check(int qty)  {
        int r = n;
        for (int i = 0; i < quantities.length && r >= 0; i++) {
            r -= quantities[i] / qty;
            if (quantities[i] % qty != 0)
                r--;
        }
        
        return r >= 0;
    }
}
