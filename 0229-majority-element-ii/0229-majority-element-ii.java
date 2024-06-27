class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (int num : nums)
            m.put(num, m.getOrDefault(num, 0) + 1);
        for (Map.Entry<Integer, Integer> e : m.entrySet())
            if (e.getValue() > nums.length / 3)
                ans.add(e.getKey());
        return ans;
    }
}