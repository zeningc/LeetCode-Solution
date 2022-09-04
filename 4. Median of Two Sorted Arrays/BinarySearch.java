class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 == 1)
            return getKthElement(nums1, 0, m, nums2, 0, n, (m + n + 1) / 2);
        return ((double)getKthElement(nums1, 0, m, nums2, 0, n, (m + n) / 2) + getKthElement(nums1, 0, m, nums2, 0, n, (m + n) / 2 + 1)) / 2;
    }
    
    int getKthElement(int[] nums1, int i, int m, int[] nums2, int j, int n, int k)  {
        if (m > n)
            return getKthElement(nums2, j, n, nums1, i, m, k);
        
        if (m == 0)
            return nums2[j + k - 1];
        
        if (k == 1)
            return Math.min(nums1[i], nums2[j]);
        
        int k1 = Math.min(k / 2, m);
        int k2 = k - k1;
        
        if (nums1[i + k1 - 1] < nums2[j + k2 - 1])
            return getKthElement(nums1, i + k1, m - k1, nums2, j, n, k - k1);
        else
            return getKthElement(nums1, i, m, nums2, j + k2, n - k2, k - k2);
    }
}
