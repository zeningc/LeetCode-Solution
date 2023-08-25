class Solution {
    public int minimumSeconds(List<Integer> nums) {
        int n = nums.size();
        Map<Integer, Integer> first = new HashMap<>();
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++)
            if (!first.containsKey(nums.get(i)))
                first.put(nums.get(i), i);
        for (int i = n - 1; i >= 0; i--)
            if (!last.containsKey(nums.get(i)))
                last.put(nums.get(i), i);
        Map<Integer, Integer> maxDist = new HashMap<>();
        Map<Integer, Integer> lastOccurance = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int num = nums.get(i);
            int dist = i - lastOccurance.getOrDefault(num, -n + last.get(num)) - 1;
            lastOccurance.put(num, i);
            if (!maxDist.containsKey(num) || maxDist.get(num) < dist)
                maxDist.put(num, dist);
        }
        
        Map<Integer, Integer> nextOccurance = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            int num = nums.get(i);
            int dist = nextOccurance.getOrDefault(num, first.get(num) + n) - i - 1;
            nextOccurance.put(num, i);
            if (!maxDist.containsKey(num) || maxDist.get(num) < dist)
                maxDist.put(num, dist);
        }
        
        int minDist = maxDist.values().stream().min(Integer::compareTo).orElse(0);
        return (minDist + 1) / 2;
    }
}

