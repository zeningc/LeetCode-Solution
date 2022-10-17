class Solution {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> tailMap = new HashMap<>();
        Map<Integer, Integer> lenMap = new HashMap<>();
        for (int num : nums)    {
            if (!tailMap.containsKey(num))
                tailMap.put(num, new PriorityQueue<>((a, b) -> a - b));
            int len = 1;
            if (tailMap.containsKey(num - 1) && !tailMap.get(num - 1).isEmpty())  {
                int tailLen = tailMap.get(num - 1).poll();
                lenMap.put(tailLen, lenMap.get(tailLen) - 1);
                len += tailLen;
            }
            tailMap.get(num).offer(len);
            lenMap.put(len, lenMap.getOrDefault(len, 0) + 1);
        }
        
        return lenMap.getOrDefault(1, 0) == 0 && lenMap.getOrDefault(2, 0) == 0;
    }
}
