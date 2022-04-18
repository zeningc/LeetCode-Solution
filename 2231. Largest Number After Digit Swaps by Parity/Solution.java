class Solution {
    public int largestInteger(int num) {
        String str = String.valueOf(num);
        int n = str.length();
        List<Integer> odds = new LinkedList<>();
        List<Integer> evens = new LinkedList<>();
        Set<Integer> oddIdx = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int curr = str.charAt(i) - '0';
            if (curr % 2 == 1) {
                odds.add(curr);
                oddIdx.add(i);
            }
            else    {
                evens.add(curr);
            }
        }
        
        Collections.sort(odds);
        Collections.reverse(odds);
        Collections.sort(evens);
        Collections.reverse(evens);
        
        int o = 0;
        int e = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (oddIdx.contains(i)) {
                ans = ans * 10 + odds.get(o++);
            }
            else    {
                ans = ans * 10 + evens.get(e++);
            }
        }
        
        return ans;
    }
}