class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 1)
            return (double)getKthElement(nums1, nums2, (m + n + 1) / 2);
        
        return ((double)getKthElement(nums1, nums2, (m + n) / 2) + getKthElement(nums1, nums2, (m + n) / 2 + 1)) / 2;
    }
    
    int getKthElement(int[] nums1, int[] nums2, int k)    {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int i = 0;
        int j = 0;
        if (nums1.length != 0){
            pq.offer(new int[] {nums1[i], 1});
        }
        
        if (nums2.length != 0)  {
            pq.offer(new int[] {nums2[j], 2});
        }
        
        int cnt = 0;
        int val = 0;
        while (cnt < k) {
            int[] curr = pq.poll();
            int label = curr[1];
            val = curr[0];
            cnt++;
            if (label == 1 && i < nums1.length - 1) {
                i++;
                pq.offer(new int[] {nums1[i], 1});
            }
            else if (label == 2 && j < nums2.length - 1)    {
                j++;
                pq.offer(new int[] {nums2[j], 2});
            }
        }
        
        return val;
    }
}
