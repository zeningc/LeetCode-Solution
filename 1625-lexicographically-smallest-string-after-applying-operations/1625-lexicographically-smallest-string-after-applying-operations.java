class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        String smallest = s;
        Queue<String> q = new LinkedList<>();
        q.offer(s);
        Set<String> seen = new HashSet<>(q);
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (smallest.compareTo(cur) > 0)
                smallest = cur;
            char[] ca = cur.toCharArray();
            for (int i = 1; i < ca.length; i += 2) // add operation.
                ca[i] = (char)((ca[i] - '0' + a) % 10 + '0');
            String addA = String.valueOf(ca);
            if (seen.add(addA))
                q.offer(addA);
            String rotate = cur.substring(n - b) + cur.substring(0, n - b); // rotation.
            if (seen.add(rotate))
                q.offer(rotate);
        }
        return smallest;
    }
}