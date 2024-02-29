class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        Map<Integer, List<Integer>> leave = new HashMap<>();
        Map<Integer, Integer> arrive = new HashMap<>();
        PriorityQueue<Integer> empty = new PriorityQueue<>();
        TreeSet<Integer> set = new TreeSet<>();
        int[] seat = new int[times.length];
        for (int i = 0; i < times.length; i++)  {
            leave.computeIfAbsent(times[i][1], x -> new ArrayList<>()).add(i);
            arrive.put(times[i][0], i);
            set.add(times[i][0]);
            set.add(times[i][1]);
        }
        int ans = 0;
        int cnt = 0;
        for (int i : set)  {
            for (int p : leave.getOrDefault(i, new ArrayList<>()))
                empty.offer(seat[p]);
            if (arrive.containsKey(i))  {
                int p = arrive.get(i);
                if (empty.isEmpty())    {
                    seat[p] = cnt++;
                }
                else    {
                    seat[p] = empty.poll();
                }
                if (p == targetFriend)
                    return seat[p];
            }
        }
        return -1;
    }
}