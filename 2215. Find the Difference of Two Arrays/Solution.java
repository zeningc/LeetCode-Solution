class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1)
            set1.add(num);
        for (int num : nums2)
            set2.add(num);
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        for (int num : set1)   {
            if (!set2.contains(num))
                l1.add(num);
        }
        for (int num : set2)   {
            if (!set1.contains(num))
                l2.add(num);
        }
        ans.add(l1);
        ans.add(l2);
        return ans;
    }
}