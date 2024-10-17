class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        
        for (int i = 0; i < k - 1; i++)
            pq.offer(new int[] {i, nums[i]});
        
        for (int i = k - 1; i < n; i++) {
            pq.offer(new int[] {i, nums[i]});
            while (!pq.isEmpty() && pq.peek()[0] < i - k + 1)
                pq.poll();
            ans.add(pq.peek()[1]);
        }
        
        int[] ansArr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            ansArr[i] = ans.get(i);
        
        return ansArr;
    }
}