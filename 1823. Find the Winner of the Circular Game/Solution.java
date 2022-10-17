class Solution {
    public int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>(n);
        for (int i = 1; i <= n; i++)
            list.add(i);
        int idx = 0;
        while (list.size() > 1) {
            idx = (idx + k - 1) % list.size();
            list.remove(idx);
        }
            
        return list.get(0);
    }
}
