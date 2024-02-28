class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        SortedList hi = new SortedList();
        SortedList lo = new SortedList();
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 1; i <= dist + 1; i++)  {
            q.offer(nums[i]);
            lo.offer(nums[i]);
            if (lo.cnt <= k - 1)
                continue;
            hi.offer(lo.pollLast());
        }
        long ans = lo.sum;
        for (int i = dist + 2; i < nums.length; i++)    {
            q.offer(nums[i]);
            lo.offer(nums[i]);
            hi.offer(lo.pollLast());
            
            int numToRemove = q.poll();
            if (numToRemove <= lo.peekLast())  {
                lo.decreaseByOne(numToRemove);
                lo.offer(hi.pollFirst());
            }
            else    {
                hi.decreaseByOne(numToRemove);
            }
            ans = Math.min(ans, lo.sum);
        }
        
        return ans + nums[0];
    }
}

class SortedList {
    TreeMap<Integer, Integer> map;
    long sum;
    int cnt;
    
    public SortedList() {
        map = new TreeMap<>();
        sum = 0;
        cnt = 0;
    }
    
    public int pollFirst() {
        return decreaseByOne(peekFirst());
    }
    
    public int pollLast() {
        return decreaseByOne(peekLast());
    }
    
    public int decreaseByOne(int key)  {
        int freq = map.get(key);
        freq--;
        if (freq == 0)
            map.remove(key);
        else
            map.put(key, freq);
        cnt--;
        sum -= key;
        return key;
    }
    
    public void offer(int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
        sum += key;
        cnt++;
        
    }
    
    public int peekFirst()  {
        return map.firstKey();
    }
    
    public int peekLast()  {
        return map.lastKey();
    }
}