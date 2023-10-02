class Solution {
    public boolean isPreorder(List<List<Integer>> nodes) {
        Map<Integer, Integer> childCnt = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        int pre = -1;
        int preParent = -1;
        Deque<Integer> stack = new LinkedList<>();
        for (List<Integer> node : nodes)
        {
            int cur = node.get(0);
            int p = node.get(1);
            if (childCnt.getOrDefault(p, 0) >= 2)
            {
                return false;
            }
            parentMap.put(cur, p);
            
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