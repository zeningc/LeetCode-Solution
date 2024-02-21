/**
 * Definition for an infinite stream.
 * class InfiniteStream {
 *     public InfiniteStream(int[] bits);
 *     public int next();
 * }
 */
class Solution {
    public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
        List<Integer> pi = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        for (int p : pattern)
            num.add(p);
        pi.add(0);
        for (int i = 1; i < num.size(); i++)  {
            int j = pi.get(i - 1);
            while (j > 0 && num.get(j) != num.get(i))
                j = pi.get(j - 1);
            if (num.get(j) == num.get(i))
                j++;
            pi.add(j);
        }
        pi.add(0);
        num.add(-1);
        for (int i = num.size(); ; i++) {
            num.add(infiniteStream.next());
            int j = pi.get(i - 1);
            while (j > 0 && num.get(j) != num.get(i))
                j = pi.get(j - 1);
            if (num.get(j) == num.get(i))
                j++;
            pi.add(j);
            if (j == pattern.length)
                return i - 2 * pattern.length;
        }
    }
}