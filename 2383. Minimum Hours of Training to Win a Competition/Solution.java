class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int energyLow = Integer.MAX_VALUE;
        int experienceLow = Integer.MAX_VALUE;
        int currEnergy = initialEnergy;
        int currExperience = initialExperience;
        
        for (int i = 0; i < energy.length; i++) {
            currEnergy -= energy[i];
            energyLow = Math.min(energyLow, currEnergy);
            currExperience -= experience[i];
            experienceLow = Math.min(experienceLow, currExperience);
            currExperience += 2 * experience[i];
        }
        
        int ans = 0;
        if (energyLow <= 0)
            ans += -energyLow + 1;
        if (experienceLow <= 0)
            ans += -experienceLow + 1;
        
        return ans;
    }
}
