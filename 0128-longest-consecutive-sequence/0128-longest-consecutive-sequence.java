class Solution {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums)    {
            if (set.contains(num))
                continue;
            if (set.contains(num + 1) && !set.contains(num - 1))  {
                int curEnd = start.get(num + 1);
                start.remove(num + 1);
                start.put(num, curEnd);
                end.put(curEnd, num);
            }
            else if (!set.contains(num + 1) && set.contains(num - 1))   {
                int curStart = end.get(num - 1);
                end.remove(curStart);
                end.put(num, curStart);
                start.put(curStart, num);
            }
            else if (set.contains(num + 1) && set.contains(num - 1))    {
                int curStart = end.get(num - 1);
                int curEnd = start.get(num + 1);
                start.remove(num + 1);
                end.remove(num - 1);
                start.put(curStart, curEnd);
                end.put(curEnd, curStart);
            }
            else    {
                start.put(num, num);
                end.put(num, num);
            }
            set.add(num);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> e : start.entrySet())
            ans = Math.max(ans, e.getValue() - e.getKey() + 1);
        return ans;
    }
}