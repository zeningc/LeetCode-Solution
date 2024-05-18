class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left <= right)   {
            int mid = left + (right - left) / 2;
            if (count(m, n, mid, k))    {
                right = mid - 1;
            }
            else    {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private boolean count(int m, int n, int mid, int k) {
        int p = 1;
        int q = n;
        int ans = 0;
        while (p < m + 1 && q > -1) {
            if (p * q > mid)    {
                q--;
            }
            else    {
                ans += q;
                p++;
            }
        }
        
        return ans >= k;
    }
}