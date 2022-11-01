class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> dup = new ArrayList<>(n);
        Set<Integer> set = new HashSet<>();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            merge(treeMap, nums[i]);
            if (set.contains(nums[i]))
                dup.add(nums[i]);
            else
                set.add(nums[i]);
        }
        int ans = 0;
        for (int num : dup) {
            Integer prev = treeMap.floorKey(num);
            int next = treeMap.get(prev) + 1;
            ans += next - num;
            merge(treeMap, next);
        }
        return ans;
        
    }
    
    void merge(TreeMap<Integer, Integer> treeMap, int num)  {
        if (treeMap.containsKey(num))
                return;
            int start = num;
            int end = num;
            Integer prev = treeMap.floorKey(start);
            if (prev != null)   {
                int prevEnd = treeMap.get(prev);
                if (prevEnd + 1 >= end) {
                    start = prev;
                    end = Math.max(end, prevEnd);
                    treeMap.remove(prev);
                }
            }
            
            Integer next = treeMap.ceilingKey(start);
            while (next != null && end + 1 >= next) {
                end = Math.max(end, treeMap.get(next));
                treeMap.remove(next);
                next = treeMap.ceilingKey(start);
            }
            
            treeMap.put(start, end);
    }
}