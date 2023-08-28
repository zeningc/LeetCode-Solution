class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        Collections.sort(offers, (a, b) -> a.get(1) - b.get(1));
        TreeMap<Integer, Integer> m = new TreeMap<>();
        m.put(-1, 0);
        for (int i = 0; i < offers.size(); i++) {
            List<Integer> offer = offers.get(i);
            int start = offer.get(0);
            int end = offer.get(1);
            int gold = offer.get(2);
            m.put(end, Math.max(m.floorEntry(end).getValue(), m.lowerEntry(start).getValue() + gold));
        }
        return m.lastEntry().getValue();
    }
}