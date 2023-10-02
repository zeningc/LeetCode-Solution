class Solution {
    public boolean isPreorder(List<List<Integer>> nodes) {
        Map<Integer, Integer> childCnt = new HashMap<>();
        int pre = -1;
        Deque<Integer> stack = new LinkedList<>();
        for (List<Integer> node : nodes)
        {
            int cur = node.get(0);
            int p = node.get(1);
            if (childCnt.getOrDefault(p, 0) >= 2)
            {
                return false;
            }
            
            while (!stack.isEmpty() && stack.peek() != p)
            {
                stack.pop();
            }
            
            if (stack.isEmpty() && pre != -1)
                return false;

            stack.push(cur);
            pre = cur;
        }
        
        return true;
    }
}