class Solution {
    public boolean isPreorder(List<List<Integer>> nodes) {
        Map<Integer, Integer> childCnt = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        int pre = -1;
        int preParent = -1;
        for (List<Integer> node : nodes)
        {
            int cur = node.get(0);
            int p = node.get(1);
            if (childCnt.getOrDefault(p, 0) >= 2)
            {
                return false;
            }
            parentMap.put(cur, p);
            boolean valid = pre == -1;
            
            int temp = pre;
            while (temp != -1)
            {
                if (p == temp)
                {
                    valid = true;
                    break;
                }
                temp = parentMap.getOrDefault(temp, -1);
            }
            if (!valid)
                return false;
            
            pre = cur;
        }
        
        return true;
    }
}