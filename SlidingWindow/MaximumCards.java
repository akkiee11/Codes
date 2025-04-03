/**
 * Problem: Maximum points from taking cards from either end of the array
 * Approach: Reverse sliding window technique
 * Time Complexity: O(k), where k is the number of cards to take
 * Space Complexity: O(1)
 */
public class MaximumCards {
    public int maxScore(int[] cardPoints, int k) {
        if (cardPoints == null || cardPoints.length == 0 || k <= 0) return 0;
        
        int n = cardPoints.length;
        
        // Get initial sum of first k cards from left
        int currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += cardPoints[i];
        }
        
        int maxSum = currentSum;
        
        // Try all combinations by removing one card from left and adding one from right
        for (int i = 0; i < k; i++) {
            currentSum -= cardPoints[k - 1 - i]; // Remove from left
            currentSum += cardPoints[n - 1 - i]; // Add from right
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
    
    public static void main(String[] args) {
        MaximumCards solution = new MaximumCards();
        
        // Test cases
        System.out.println(solution.maxScore(new int[]{1,2,3,4,5,6,1}, 3)); // Expected: 12
        System.out.println(solution.maxScore(new int[]{2,2,2}, 2)); // Expected: 4
        System.out.println(solution.maxScore(new int[]{9,7,7,9,7,7,9}, 7)); // Expected: 55
        System.out.println(solution.maxScore(new int[]{1,1000,1}, 1)); // Expected: 1
        System.out.println(solution.maxScore(new int[]{1,79,80,1,1,1,200,1}, 3)); // Expected: 202
    }
}