class Solution {
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int lo = 0;
        int cnt = 0;
        int last = -1;
        int ret = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int hi = 0; hi < n; hi++)  {
            int cur = s.charAt(hi) - '0';
            if (last == cur)    {
                cnt++;
                q.offer(hi - 1);
                while (cnt > 1) {
                    if (lo == q.peek()) {
                        cnt--;
                        q.poll();
                    }
                    lo++;
                }
            }
            last = cur;
            ret = Math.max(ret, hi - lo + 1);
        }
        return ret;
    }
}