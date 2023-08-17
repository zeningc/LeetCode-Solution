class Solution {
    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        if (n == 1)
            return 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] indeg = new int[n];
        int totalSum = 0;
        for (int i = 0; i < n; i++)
            totalSum += nums[i];
        List<Integer> targetSumList = new ArrayList<>((int)Math.sqrt(totalSum));
        for (int[] edge : edges)    {
            int u = edge[0];
            int v = edge[1];
            if (!map.containsKey(u))
                map.put(u, new LinkedList<>());
            if (!map.containsKey(v))
                map.put(v, new LinkedList<>());
            map.get(u).add(v);
            map.get(v).add(u);
            indeg[v]++;
            indeg[u]++;
        }
        for (int i = 1; i < totalSum; i++)    {
            if (totalSum % i != 0)   {
                continue;
            }
            int target = i;
            int[] indegCopy = indeg.clone();
            int[] sum = new int[n];
            Queue<Integer> q = new LinkedList<>();
            for (int j = 0; j < n; j++)
                if (indegCopy[j] == 1)
                    q.offer(j);
            boolean flag = false;
            while (!q.isEmpty())    {
                int u = q.poll();
                sum[u] += nums[u];
                if (sum[u] == target)
                    sum[u] = 0;
                
                if (sum[u] > target)    {
                    flag = true;
                    break;
                }
                
                for (int v : map.get(u))    {
                    indegCopy[v]--;
                    sum[v] += sum[u];
                    if (indegCopy[v] == 1)
                        q.offer(v);
                }
            }
            
            if (!flag)
                return totalSum / target - 1;
                    
        }
        
        return 0;
    }
}