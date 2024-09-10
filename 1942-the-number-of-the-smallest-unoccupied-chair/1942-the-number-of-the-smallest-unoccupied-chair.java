class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        Map<Integer, Integer> arrivalTime = new HashMap<>();
        Map<Integer, List<Integer>> leaveTime = new HashMap<>();
        Map<Integer, Integer> assignment = new HashMap<>();
        TreeMap<Integer, Integer> availableSeats = new TreeMap<>();
        availableSeats.put(0, Integer.MAX_VALUE);
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
                int lo = seatToRelease;
                int hi = seatToRelease;
                Integer lowerKey = availableSeats.lowerKey(seatToRelease);
                if (lowerKey != null && seatToRelease - 1 == availableSeats.get(lowerKey))   {
                    lo = lowerKey;
                    availableSeats.remove(lowerKey);
                }
                Integer higherKey = availableSeats.higherKey(seatToRelease);
                if (higherKey != null && seatToRelease + 1 == higherKey)  {
                    hi = availableSeats.get(higherKey);
                    availableSeats.remove(higherKey);
                }
                availableSeats.put(lo, hi);
            }
            if (!arrivalTime.containsKey(time))
                continue;
            int idx = arrivalTime.get(time);
            Integer from = availableSeats.firstKey();
            Integer to = availableSeats.get(from);
            if (idx == targetFriend)
                return from;
            assignment.put(idx, from);
            availableSeats.remove(from);
            if (from + 1 <= to)
                availableSeats.put(from + 1, to);
        }
        
        return -1;
    }
}
/*
treemap[start, end]

*/