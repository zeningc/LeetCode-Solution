class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int skillCnt = 0;
        Map<String, Integer> skillIdMap = new HashMap<>();
        Map<Long, Integer> cache = new HashMap<>();
        for (String skill : req_skills)
            skillIdMap.put(skill, skillCnt++);
        int requireSkillBit = (1 << skillCnt) - 1;
        int[] peopleSkillBit = new int[people.size()];
        for (int i = 0; i < people.size(); i++)  {
            List<String> skills = people.get(i);
            int skillBit = 0;
            for (String skill : skills)
                skillBit |= (1 << skillIdMap.get(skill));
            peopleSkillBit[i] = skillBit;
        }
        long[] dp = new long[1 << skillCnt];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 0; i < (1 << skillCnt); i++)   {
            for (int j = 0; j < people.size(); j++) {
                if (cnt(cache, dp[i]) + 1 < cnt(cache, dp[i | peopleSkillBit[j]]))
                    dp[i | peopleSkillBit[j]] = dp[i] | (1L << j);
            }
        }
        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < people.size(); i++)
            if ((dp[(1 << skillCnt) - 1] & (1L << i)) != 0)
                ansList.add(i);
        
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++)
            ans[i] = ansList.get(i);
        
        return ans;
    }
    
    int cnt(Map<Long, Integer> cache, long bit)    {
        if (cache.containsKey(bit))
            return cache.get(bit);
        int cnt = 0;
        long key = bit;
        while (bit != 0)    {
            bit &= (bit - 1);
            cnt++;
        }
        cache.put(key, cnt);
        return cnt;
    }
}