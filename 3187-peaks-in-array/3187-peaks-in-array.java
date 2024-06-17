class Solution {
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
        List<Integer> peaks = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 1; i < n - 1; i++) {
            if (nums[i + 1] < nums[i] && nums[i] > nums[i - 1]) {
                peaks.add(i);
            }
        }
        
        for (int[] q : queries) {
            if (q[0] == 2) {
                nums[q[1]] = q[2];
                for (int i = q[1] - 1; i <= q[1] + 1; i++) {
                    if (i > 0 && i < n - 1) {
                        int index = bisectLeft(peaks, i);
                        if (nums[i + 1] < nums[i] && nums[i] > nums[i - 1]) {
                            if (index == peaks.size() || peaks.get(index) != i) {
                                peaks.add(index, i);
                            }
                        } else if (index != peaks.size() && peaks.get(index) == i) {
                            peaks.remove(index);
                        }
                    }
                }
            } else {
                int i1 = bisectLeft(peaks, q[1] + 1);
                int i2 = bisectRight(peaks, q[2] - 1);
                res.add(i2 - i1 >= 0 ? i2 - i1 : 0);
            }
        }
        return res;
    }
    
    private int bisectLeft(List<Integer> list, int i)   {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) >= i)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    private int bisectRight(List<Integer> list, int i)   {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo <= hi)    {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) > i)
                hi = mid - 1;
            else
                lo = mid + 1;
        }
        
        return lo;
    }
}