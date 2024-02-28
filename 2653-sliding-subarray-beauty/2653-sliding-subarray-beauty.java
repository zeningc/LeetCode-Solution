class Solution {
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        SortedList min = new SortedList();
        SortedList max = new SortedList();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            q.offer(nums[i]);
            if (min.cnt < x)    {
                min.offer(nums[i]);
                continue;
            }
            min.offer(nums[i]);
            max.offer(min.pollLast());
        }
        int[] ans = new int[nums.length - k + 1];
        ans[0] = min.peekLast() < 0 ? min.peekLast() : 0;
        for (int i = k; i < nums.length; i++)   {
            q.offer(nums[i]);
            min.offer(nums[i]);
            max.offer(min.pollLast());
            int numToRemove = q.poll();
            if (numToRemove <= min.peekLast())   {
                min.decreaseByOne(numToRemove);
                min.offer(max.pollFirst());
            }
            else    {
                max.decreaseByOne(numToRemove);
            }
            ans[i - k + 1] = min.peekLast() < 0 ? min.peekLast() : 0;
        }
        
        return ans;
    }
}

class SortedList {
    TreeMap<Integer, Integer> map;
    int sum;
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