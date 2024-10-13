class Solution {
    public int[] resultArray(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums)
            pq.offer(num);
        Map<Integer, Integer> numToId = new HashMap<>();
        int cnt = 0;
        while (!pq.isEmpty())   {
            int pop = pq.poll();
            if (numToId.containsKey(pop))
                continue;
            numToId.put(pop, cnt++);
        }
        
        int[] tree1 = new int[cnt + 1];
        int[] tree2 = new int[cnt + 1];
        List<Integer> num1 = new ArrayList<>();
        List<Integer> num2 = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)   {
            if (i == 0) {
                num1.add(nums[i]);
                update(tree1, numToId.get(nums[i]), 1);
                continue;
            }
            if (i == 1) {
                num2.add(nums[i]);
                update(tree2, numToId.get(nums[i]), 1);
                continue;
            }
            
            int cnt1 = rangeQuery(tree1, numToId.get(nums[i]) + 1, cnt - 1);
            int cnt2 = rangeQuery(tree2, numToId.get(nums[i]) + 1, cnt - 1);
            if (cnt1 > cnt2 || cnt1 == cnt2 && num1.size() < num2.size() || cnt1 == cnt2 && num1.size() == num2.size())    {
                num1.add(nums[i]);
                update(tree1, numToId.get(nums[i]), 1);
            }
            else    {
                num2.add(nums[i]);
                update(tree2, numToId.get(nums[i]), 1);
            }
            
        }
        
        int[] ans = new int[nums.length];
        int i = 0;
        for (int num : num1)
            ans[i++] = num;
        for (int num : num2)
            ans[i++] = num;
        return ans;
    }
    
    
    void update(int[] tree, int idx, int delta) {
        int n = tree.length;
        
        int p = idx + 1;
        while (p < n)   {
            tree[p] += delta;
            p += (p & -p);
        }
        
        return;
    }
    
    int presum(int[] tree, int idx)    {
        int p = idx;
        int sum = 0;
        while (p > 0)   {
            sum += tree[p];
            p -= (p & -p);
        }
        
        return sum;
    }
    
    int rangeQuery(int[] tree, int left, int right)    {
        if (left > right)
            return 0;
        return presum(tree, right + 1) - presum(tree, left);
    }
}

