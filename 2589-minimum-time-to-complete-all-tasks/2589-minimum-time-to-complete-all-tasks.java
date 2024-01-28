class Solution {
    public int findMinimumTime(int[][] tasks) {
        List<int[]> arr = new ArrayList<>();
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        for (int[] task : tasks)    {
            int start = task[0];
            int end = task[1];
            int lo = 0;
            int hi = arr.size() - 1;
            while (lo <= hi)    {
                int mid = lo + (hi - lo) / 2;
                if (arr.get(mid)[0] >= start)
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            
            int overlap = 0;
            if (!arr.isEmpty())
                overlap += arr.get(arr.size() - 1)[2] - (hi >= 0 ? arr.get(hi)[2] : 0);
            if (hi >= 0 && arr.get(hi)[1] >= start)
                overlap += arr.get(hi)[1] - start + 1;
            
            int duration = task[2] - overlap;
            if (duration <= 0)
                continue;
            int totalDuration = duration + (arr.isEmpty() ? 0 : arr.get(arr.size() - 1)[2]);
            int curEnd = end;
            int curStart = end;
            while (!arr.isEmpty() && duration > 0)  {
                int consume = Math.min(curEnd - arr.get(arr.size() - 1)[1], duration);
                duration -= consume;
                if (consume < curEnd - arr.get(arr.size() - 1)[1])  {
                    curStart = curEnd - consume + 1;
                    break;
                }
                curStart = arr.get(arr.size() - 1)[0];
                curEnd = curStart - 1;
                arr.remove(arr.size() - 1);
            }
            if (duration > 0)
                curStart = curStart - duration;
            arr.add(new int[] {curStart, end, totalDuration});
        }
        
        
        return arr.isEmpty() ? 0 : arr.get(arr.size() - 1)[2];
    }
}