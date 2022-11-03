class Solution {
    int[] count;
    int n;
    public List<Integer> countSmaller(int[] nums) {
        n = nums.length;
        count = new int[n];
        List<int[]> numList = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
            numList.add(new int[] {nums[i], i});
        List<int[]> ret = mergeSort(numList, 0, n - 1);
        return Arrays.stream(count).boxed().collect(Collectors.toList());
    }
    
    List<int[]> mergeSort(List<int[]> numList, int lo, int hi)  {
        List<int[]> ret = new ArrayList<>(hi - lo + 1);
        if (lo == hi)   {
            ret.add(numList.get(lo));
            return ret;
        }
        
        if (lo + 1 == hi)   {
            if (numList.get(lo)[0] <= numList.get(hi)[0])    {
                ret.add(numList.get(hi));
                ret.add(numList.get(lo));
            }
            else    {
                count[numList.get(lo)[1]]++;
                ret.add(numList.get(lo));
                ret.add(numList.get(hi));
            }
            flush(numList, ret, lo);
            return ret;
        }
        
        int mid = lo + (hi - lo) / 2;
        List<int[]> left = mergeSort(numList, lo, mid);
        List<int[]> right = mergeSort(numList, mid + 1, hi);
        
        for (int[] item : left) {
            int num = item[0];
            int idx = item[1];
            int a = 0;
            int b = right.size() - 1;
            while (a <= b)  {
                int m = a + (b - a) / 2;
                if (right.get(m)[0] < num)  {
                    b = m - 1;
                }
                else    {
                    a = m + 1;
                }
            }
            count[idx] += right.size() - a;
        }
        
        int p = 0;
        int q = 0;
        while (p < left.size() && q < right.size()) {
            if (left.get(p)[0] < right.get(q)[0])   {
                ret.add(right.get(q++));
            }
            else    {
                ret.add(left.get(p++));
            }
        }
        
        while (p < left.size()) {
            ret.add(left.get(p++));
        }
        
        while (q < right.size()) {
            ret.add(right.get(q++));
        }
        
        flush(numList, ret, lo);
        
        return ret;
    }
    void flush(List<int[]> numList, List<int[]> ret, int lo)    {
        for (int i = 0; i < ret.size(); i++)    {
            numList.set(i + lo, ret.get(i));
        }
    }
}