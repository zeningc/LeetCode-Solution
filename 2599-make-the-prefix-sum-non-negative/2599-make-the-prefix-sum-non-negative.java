class Solution {
    public int makePrefSumNonNegative(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long prefix = 0;
        int ans = 0;
        List<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        int size = arr.size();
        int i = 0;
        while (i < size)    {
            prefix += arr.get(i);
            pq.offer(arr.get(i));
            while (prefix < 0)  {
                int poll = pq.poll();
                prefix -= poll;
                arr.add(poll);
                ans++;
            }
            i++;
        }
        return ans;
    }
}