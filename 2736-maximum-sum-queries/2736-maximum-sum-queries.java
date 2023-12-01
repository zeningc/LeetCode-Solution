class Solution {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] nums = new int[n][3];
        int[][] qss = new int[queries.length][3];
        for (int i = 0; i < queries.length; i++)
            qss[i] = new int[] {queries[i][0], queries[i][1], i};
        for (int i = 0; i < n; i++)
            nums[i] = new int[] {nums1[i], nums2[i], nums1[i] + nums2[i]};
        Arrays.sort(nums, (a, b) -> b[2] - a[2]);
        Arrays.sort(qss, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        List<Pair<Integer, List<int[]>>> qs = new LinkedList<>();
        int p = 0;
        while (p < qss.length)  {
            int q = p;
            while (q < qss.length && qss[q][0] == qss[p][0])
                q++;
            List<int[]> list = new LinkedList<>();
            qs.add(new Pair<Integer, List<int[]>>(qss[p][0], list));
            for (int i = p; i < q; i++)
                list.add(new int[] {qss[i][1], qss[i][2]});
            p = q;
        }
        
        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n && !qs.isEmpty(); i++)   {
            ListIterator<Pair<Integer, List<int[]>>> iterator = qs.listIterator();
            while (iterator.hasNext()) {
                Pair<Integer, List<int[]>> pair = iterator.next();
                int x = pair.getKey();
                if (x > nums[i][0])
                    break;
                ListIterator<int[]> innerIterator = pair.getValue().listIterator();
                while (innerIterator.hasNext()) {
                    int[] cur = innerIterator.next();
                    int y = cur[0];
                    int idx = cur[1];
                    if (y > nums[i][1])
                        break;
                    ans[idx] = nums[i][2];
                    innerIterator.remove();
                }
                if (pair.getValue().isEmpty())
                    iterator.remove();
            }
        }
        
        return ans;
        
    }
}