/**
 * Find the Duplicate Number
 * 
 * Problem Description:
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find this duplicate one using Floyd's Cycle Detection Algorithm.
 * 
 * Example 1:
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 * 
 * Example 2:
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 * 
 * Constraints:
 * - 1 <= n <= 10^5
 * - nums.length == n + 1
 * - 1 <= nums[i] <= n
 * - All the integers in nums appear only once except for precisely one integer which appears two or more times.
 * 
 * Approach:
 * Floyd's Cycle Detection (Similar to LinkedList Cycle):
 * - Treat array values as pointers to next indices
 * - Use two pointers: slow (moves one step) and fast (moves two steps)
 * - First find the intersection point inside the cycle
 * - Then find the entrance to the cycle (duplicate number)
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class FindDuplicateNumber {
    
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }
        
        // Phase 1: Finding intersection point inside the cycle
        int slow = nums[0];
        int fast = nums[0];
        
        do {
            slow = nums[slow];           // Move one step
            fast = nums[nums[fast]];      // Move two steps
        } while (slow != fast);
        
        // Phase 2: Finding the entrance to the cycle (duplicate number)
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        
        return slow;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 3, 4, 2, 2},     // Standard case
            {3, 1, 3, 4, 2},      // Duplicate at different position
            {1, 1},               // Smallest possible array with duplicate
            {2, 2, 2, 2},         // Multiple occurrences of same number
            {1, 2, 3, 4, 4, 5}    // Larger array
        };
        
        // Run test cases
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("Input array: " + java.util.Arrays.toString(testCases[i]));
            int duplicate = findDuplicate(testCases[i]);
            System.out.println("Duplicate number: " + duplicate);
        }
    }
}