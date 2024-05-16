class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        List<Integer> neg1 = new ArrayList<>();
        List<Integer> neg2 = new ArrayList<>();
        List<Integer> neg1Reverse = new ArrayList<>();
        List<Integer> neg2Reverse = new ArrayList<>();
        List<Integer> pos1 = new ArrayList<>();
        List<Integer> pos2 = new ArrayList<>();
        List<Integer> pos1Reverse = new ArrayList<>();
        List<Integer> pos2Reverse = new ArrayList<>();
        for (int num : nums1)
            if (num < 0){
                neg1.add(num);
                neg1Reverse.add(num);
            }
            else    {
                pos1.add(num);
                pos1Reverse.add(num);
            }
        
        for (int num : nums2)
            if (num < 0)    {
                neg2.add(num);
                neg2Reverse.add(num);
            }
            else    {
                pos2.add(num);
                pos2Reverse.add(num);
            }
        
        Collections.reverse(neg1Reverse);
        Collections.reverse(neg2Reverse);
        Collections.reverse(pos1Reverse);
        Collections.reverse(pos2Reverse);
        long lo = -(long)1e10;
        long hi = (long)1e10;
        
        while (lo <= hi)    {
            long mid = lo + (hi - lo) / 2;
            if (check(neg1, neg2, neg1Reverse, neg2Reverse, pos1, pos2, pos1Reverse, pos2Reverse, mid, k))
                hi = mid - 1;
             else
                lo = mid + 1;
        }
        
        return lo;
    }
    
    boolean check(List<Integer> neg1, List<Integer> neg2, List<Integer> neg1Reverse, List<Integer> neg2Reverse, List<Integer> pos1, List<Integer> pos2, List<Integer> pos1Reverse, List<Integer> pos2Reverse, long mid, long k)    {
        long cnt = 0;
        if (mid < 0)
            cnt = count(neg1, pos2Reverse, mid) + count(pos1Reverse, neg2, mid);
        else
            cnt = count(pos1, pos2, mid) + count(neg1Reverse, neg2Reverse, mid)
     + neg1.size() * pos2.size() + neg2.size() * pos1.size();
        
        return cnt >= k;
    }
    
    long count(List<Integer> l1, List<Integer> l2, long target)  {
        if (l1.isEmpty() || l2.isEmpty())
            return 0;
        if ((long)l1.get(l1.size() - 1) * l2.get(l2.size() - 1) <= target)
            return (long)l1.size() * l2.size();
        int p = 0;
        int q = l2.size() - 1;
        long cnt = 0;
        while (p <= l1.size() && q >= 0) {
            if (p < l1.size() && (long)l1.get(p) * l2.get(q) <= target) {
                p++;
                continue;
            }
            cnt += p;
            q--;
        }
        
        return cnt;
    }
    
}