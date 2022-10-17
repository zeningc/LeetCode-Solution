class Solution {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int a : arr)
            freq.put(a, freq.getOrDefault(a, 0) + 1);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freq.get(b) - freq.get(a));
        
        for (int key : freq.keySet())
            pq.offer(key);
        
        int n = arr.length;
        int sum = 0;
        int cnt = 0;
        while (!pq.isEmpty() && sum < n / 2)    {
            sum += freq.get(pq.poll());
            cnt++;
        }
        
        return cnt;
    }
}
