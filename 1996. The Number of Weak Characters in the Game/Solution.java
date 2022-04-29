class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (a, b) -> (a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]));
        Deque<int[]> stk = new LinkedList<>();
        int ans = 0;
        for (int i = 0; i < properties.length; i++) {
            while (!stk.isEmpty() && stk.peek()[0] < properties[i][0] && stk.peek()[1] < properties[i][1])    {
                stk.pop();
                ans++;
            }
            stk.push(properties[i]);
        }
        
        return ans;
    }
}
