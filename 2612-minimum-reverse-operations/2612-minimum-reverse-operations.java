import java.util.NavigableSet;

class Solution {
    public int[] minReverseOperations(int n, int p, int[] banned, int k) {
        boolean[] skip = new boolean[n];
        for (int ban : banned)
            skip[ban] = true;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        Deque<Integer> q = new LinkedList<>();
        TreeSet<Integer> odd = new TreeSet<>();
        TreeSet<Integer> even = new TreeSet<>();
        for (int i = 0; i < n; i++)
            if (i % 2 == 0 && !skip[i] && i != p)
                even.add(i);
            else if (i % 2 == 1 && !skip[i] && i != p)
                odd.add(i);
        
        q.offer(p);
        int cnt = 0;
        while (!q.isEmpty())    {
            int size = q.size();
            while (size-- > 0)  {
                int x = q.poll();
                ans[x] = cnt;
                int minArrStart = Math.max(0, x - k + 1);
                int maxArrEnd = Math.min(n - 1, x + k - 1);
                int lo = 2 * minArrStart + k - x - 1;
                int hi = 2 * maxArrEnd - x - k + 1;
                TreeSet<Integer> set;
                set = lo % 2 == 0 ? even : odd;
                NavigableSet<Integer> subSet = set.subSet(lo, true, hi, true); 
                Iterator<Integer> iterator = subSet.iterator();
                while (iterator.hasNext()) {
                    Integer nxt = iterator.next();
                    q.offer(nxt);
                    iterator.remove();
                }
            }
            cnt++;
        }
        
        return ans;
    }
}