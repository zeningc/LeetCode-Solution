class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        Map<Integer, Integer> arrivalTime = new HashMap<>();
        Map<Integer, List<Integer>> leaveTime = new HashMap<>();
        Map<Integer, Integer> assignment = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < times.length; i++)
            pq.offer(i);
        Set<Integer> timeSet = new HashSet<>();
        for (int i = 0; i < times.length; i++)  {
            arrivalTime.put(times[i][0], i);
            leaveTime.computeIfAbsent(times[i][1], x -> new ArrayList<>()).add(i);
            timeSet.add(times[i][0]);
            timeSet.add(times[i][1]);
        }
        List<Integer> timeList = new ArrayList<>(timeSet);
        Collections.sort(timeList);
        for (int time : timeList)   {
            for (int leaveFriendIdx : leaveTime.getOrDefault(time, new ArrayList<>()))  {
                int seatToRelease = assignment.get(leaveFriendIdx);
                pq.offer(seatToRelease);
            }
            if (!arrivalTime.containsKey(time))
                continue;
            int idx = arrivalTime.get(time);
            int seatToAssign = pq.poll();
            if (idx == targetFriend)
                return seatToAssign;
            assignment.put(idx, seatToAssign);
        }
        
        return -1;
    }
}
/*
treemap[start, end]

*/