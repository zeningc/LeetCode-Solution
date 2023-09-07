class Solution {
    public int findMinimumTime(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        List<int[]> list = new ArrayList<>(2000);
        list.add(new int[] {-1, -1, 0});
        for (int[] task : tasks)    {
            int start = task[0];
            int end = task[1];
            int duration = task[2];
            int lo = 0;
            int hi = list.size() - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (list.get(mid)[0] > start)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            
            int idx = hi;
            int overlap = 0;
            if (list.get(idx)[1] >= start)
                overlap += Math.min(end, list.get(idx)[1]) - start + 1;
            
            overlap += list.get(list.size() - 1)[2] - list.get(idx)[2];
            
            if (overlap < duration) {
                int newEnd = end;
                int newTotal = duration - overlap;
                int newStart = newEnd - newTotal + 1;
                int preTotal = list.get(list.size() - 1)[2];
                newTotal += preTotal;
                while (!list.isEmpty() && list.get(list.size() - 1)[1] >= newStart - 1)  {
                    int preStart = list.get(list.size() - 1)[0];
                    int preEnd = list.get(list.size() - 1)[1];
                    int gap = preEnd + 1 - newStart;
                    newStart = preStart - gap;
                    list.remove(list.size() - 1);
                }
                list.add(new int[] {newStart, newEnd, newTotal});
            }
        }
        
        return list.get(list.size() - 1)[2];
    }
}

