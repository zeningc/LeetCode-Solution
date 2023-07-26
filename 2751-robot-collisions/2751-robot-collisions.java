class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        Integer[] idxes = new Integer[n];
        for (int i = 0; i < n; i++)
            idxes[i] = i;
        Arrays.sort(idxes, (a, b) -> positions[a] - positions[b]);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int curIdx = idxes[i];
            int curHealth = healths[curIdx];
            char curDir = directions.charAt(curIdx);
            boolean curRemoved = false;
            while (!curRemoved && !stack.isEmpty() && directions.charAt(stack.peek()) == 'R' && curDir == 'L')   {
                boolean preRemoved = false;
                int preIdx = stack.peek();
                int preHealth = healths[preIdx];
                if (preHealth < curHealth)  {
                    preRemoved = true;
                    curHealth--;
                }
                else if (preHealth == curHealth)  {
                    preRemoved = true;
                    curRemoved = true;
                }
                else    {
                    curRemoved = true;
                    preHealth--;
                }
                
                if (preRemoved)
                    stack.pop();
                else
                    healths[preIdx] = preHealth;
            }
            
            if (!curRemoved)    {
                stack.push(curIdx);
                healths[curIdx] = curHealth;
            }
        }
        
        int[] leftIdxArray = new int[stack.size()];
        int i = 0;
        while (!stack.isEmpty())
            leftIdxArray[i++] = stack.pollLast();
        Arrays.sort(leftIdxArray);
        
        List<Integer> ret = new LinkedList<>();
        for (int idx : leftIdxArray)
            ret.add(healths[idx]);
        
        return ret;
    }
}