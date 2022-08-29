class SparseVector {
    Map<Integer,Integer> store = new HashMap<>();
    SparseVector(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            if(nums[i]!=0)  {
                store.put(i,nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Map<Integer,Integer> store2=vec.store;
        int ans=0;
        for(int key:store.keySet()) {
            if(store2.containsKey(key)) {
                ans+=store.get(key)*store2.get(key);
            }
        }
        return ans;
    }
}