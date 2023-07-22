class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int maxLeft = IntStream.of(left).max().orElse(0);
        int maxRight = IntStream.of(right).map(num -> n - num).max().orElse(0);
        return Math.max(maxLeft, maxRight);
    }
}