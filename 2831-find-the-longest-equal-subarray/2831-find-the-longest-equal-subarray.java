class Solution {
    public int longestEqualSubarray(List<Integer> nums, int k) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        Map<Integer, Integer> ptrs = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.size(); i++)   {
            m.computeIfAbsent(nums.get(i), x -> new ArrayList<>()).add(i);
            int ptr = ptrs.getOrDefault(nums.get(i), 0);
            List<Integer> list = m.getOrDefault(nums.get(i), new ArrayList<>());
            while (ptr < list.size() && i - list.get(ptr) + 1 - (list.size() - ptr) > k)   {
                ptr++;
            }
            ptrs.put(nums.get(i), ptr);
            ans = Math.max(ans, list.size() - ptr);
        }
        return ans;
    }
}