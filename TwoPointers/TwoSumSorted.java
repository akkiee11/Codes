/**
 * Two Sum in Sorted Array
 * 
 * Problem Description:
 * Given a sorted array of integers and a target sum, find two numbers in the array
 * that add up to the target sum. Return the indices of these two numbers.
 * The array is 1-indexed, which means the first element is at index 1.
 * 
 * Example:
 * Input: numbers = [2, 7, 11, 15], target = 9
 * Output: [1, 2]
 * Explanation: numbers[1] + numbers[2] = 2 + 7 = 9
 * 
 * Approaches:
 * 1. Brute Force Approach (O(n²)):
 *    - Use nested loops to check every possible pair of numbers
 *    - For each number, check if there exists another number that sums to target
 *    - Time Complexity: O(n²), Space Complexity: O(1)
 * 
 * 2. Two Pointer Approach (O(n)):
 *    - Use two pointers: left (starting from beginning) and right (starting from end)
 *    - Move pointers based on sum comparison with target
 *    - Time Complexity: O(n), Space Complexity: O(1)
 */

public class TwoSumSorted {
    
    // Two Pointer Approach - More Efficient
    public static int[] findTwoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];
            
            if (currentSum == target) {
                // Adding 1 to convert to 1-based indexing
                return new int[]{left + 1, right + 1};
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        // If no solution is found
        return new int[]{-1, -1};
    }
    
    // Brute Force Approach - Less Efficient
    public static int[] findTwoSumBruteForce(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    // Adding 1 to convert to 1-based indexing
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        
        // If no solution is found
        return new int[]{-1, -1};
    }
    
    public static void main(String[] args) {
        // Test case
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        
        // Test Two Pointer approach
        int[] result = findTwoSum(numbers, target);
        System.out.println("Two Pointer Approach Result: [" + result[0] + ", " + result[1] + "]");
        
        // Test Brute Force approach
        result = findTwoSumBruteForce(numbers, target);
        System.out.println("Brute Force Approach Result: [" + result[0] + ", " + result[1] + "]");
    }
}