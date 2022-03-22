class Solution {
    public int halveArray(int[] nums) {
        double sum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        for (int num : nums)    {
            sum += num * 1.0;
            pq.offer(num * 1.0);
        }
        
        double target = sum / 2;
        double minus = 0;
        int ans = 0;
        while (minus < target)  {
            double largest = pq.poll();
            minus += largest / 2;
            pq.offer(largest / 2);
            ans++;
        }
        
        return ans;
    }
}