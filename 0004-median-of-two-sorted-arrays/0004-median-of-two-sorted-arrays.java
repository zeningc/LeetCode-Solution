class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        return (findKthSmallest(nums1, nums2, 0, 0, (size + 1) / 2) + findKthSmallest(nums1, nums2, 0, 0, (size + 2) / 2)) / 2;
    }
    
    double findKthSmallest(int[] nums1, int[] nums2, int start1, int start2, int k)    {
        if (nums1.length == start1)
            return nums2[start2 + k - 1];
        if (nums2.length == start2)
            return nums1[start1 + k - 1];
        if (k == 1)
            return Math.min(nums1[start1], nums2[start2]);
        int consume = Math.min(k / 2, Math.min(nums1.length - start1, nums2.length - start2));
        if (nums1[start1 + consume - 1] < nums2[start2 + consume - 1])
            return findKthSmallest(nums1, nums2, start1 + consume, start2, k - consume);
        return findKthSmallest(nums1, nums2, start1, start2 + consume, k - consume);
    }
}