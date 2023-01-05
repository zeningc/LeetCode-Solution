class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int n = price.length;
        int lo = 0;
        int hi = price[n - 1] - price[0];
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (!check(price, mid, k - 1))
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        return hi;
    }
    
    boolean check(int[] price, int gap, int k)  {
        int i = 0;
        int n = price.length;
        while (i < n && k > 0)    {
            int j = i + 1;
            while (j < n && price[j] - price[i] < gap)  {
                j++;
            }
            if (j < n)
                k--;
            i = j;
        }
        
        return k <= 0;
    }
    
}
// o o o o x x x x