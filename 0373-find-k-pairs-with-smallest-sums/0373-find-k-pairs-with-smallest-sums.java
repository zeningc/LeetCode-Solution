class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]);
        for (int i = 0; i < nums2.length; i++)
            pq.offer(new int[] {0, i});
        List<List<Integer>> ans = new ArrayList<>();
        while (k != 0 && !pq.isEmpty())   {
            int[] cur = pq.poll();
            ans.add(Arrays.asList(nums1[cur[0]], nums2[cur[1]]));
            k--;
            if (cur[0] < nums1.length - 1)
                pq.offer(new int[] {cur[0] + 1, cur[1]});
        }
        
        return ans;
    }
}