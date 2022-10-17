class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        TreeMap<Integer, Integer> rangeMap = new TreeMap<>();
        Map<Integer, Integer> arriveMap = new HashMap<>();
        Map<Integer, List<Integer>> leaveMap = new HashMap<>();
        Map<Integer, Integer> chairMap = new HashMap<>();
        TreeSet<Integer> timeSet = new TreeSet<>();
        for (int i = 0; i < times.length; i++)    {
            int arrive = times[i][0];
            int leave = times[i][1];
            timeSet.add(arrive);
            timeSet.add(leave);
            if (!leaveMap.containsKey(leave))
                leaveMap.put(leave, new LinkedList<>());
            arriveMap.put(arrive, i);
            leaveMap.get(leave).add(i);
        }
        
        for (int t : timeSet)    {
            if (leaveMap.containsKey(t))    {
                for (int p : leaveMap.get(t))   {
                    int pos = chairMap.get(p);
                    Integer start = rangeMap.floorKey(pos);
                    int end = rangeMap.get(start);
                    rangeMap.remove(start);
                    if (pos != start)   {
                        rangeMap.put(start, pos - 1);
                    }
                    if (pos != end) {
                        rangeMap.put(pos + 1, end);
                    }
                    chairMap.remove(p);
                }
            }
            
            if (arriveMap.containsKey(t))   {
                int p = arriveMap.get(t);
                int pos = 0;
                if (!rangeMap.isEmpty())    {
                    Integer first = rangeMap.firstKey();
                    if (first > 0)
                        pos = 0;
                    else    {
                        pos = rangeMap.get(first) + 1;
                    }
                }
                
                if (p == targetFriend)
                    return pos;
                
                Integer prev = rangeMap.floorKey(pos);
                Integer next = rangeMap.ceilingKey(pos);
                int start = pos;
                int end = pos;
                if (prev != null && rangeMap.get(prev) == pos - 1) {
                    start = prev;
                    end = pos;
                    rangeMap.remove(prev);
                }
                
                if (next != null && next == pos + 1)    {
                    end = rangeMap.get(next);
                    rangeMap.remove(next);
                }
                
                rangeMap.put(start, end);
                chairMap.put(p, pos);
            }
        }
        
        return -1;
    }
}

