class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        int[] peopleSkill = new int[people.size()];
        Map<String, Integer> skillToIdx = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) {
            skillToIdx.put(req_skills[i], i);
        }
        for (int i = 0; i < people.size(); i++) {
            for (String skill : people.get(i))  {
                peopleSkill[i] |= (1 << skillToIdx.get(skill));
            }
        }
        
        
        int[] dp = new int[1 << n];
        List<Integer>[] peopleNeeded = new List[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        for (int i = 0; i < (1 << n); i++)
            peopleNeeded[i] = new LinkedList<>();
        
        for (int i = 1; i <= people.size(); i++) {
            
            for (int state = 0; state < (1 << n); state++)  {
                if (dp[state] == -1)
                    continue;
                
                if (dp[state | peopleSkill[i - 1]] == -1 || dp[state] + 1 < dp[state | peopleSkill[i - 1]])    {
                    dp[state | peopleSkill[i - 1]] = dp[state] + 1;
                    List<Integer> t = new LinkedList<>(peopleNeeded[state]);
                    t.add(i - 1);
                    peopleNeeded[state | peopleSkill[i - 1]] = t;
                    continue;
                }
                
            }
        }
        
        int[] ans = new int[peopleNeeded[(1 << n) - 1].size()];
        for (int i = 0; i < peopleNeeded[(1 << n) - 1].size(); i++)
            ans[i] = peopleNeeded[(1 << n) - 1].get(i);
        
        return ans;
    }
}
