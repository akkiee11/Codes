/**
 * Trapping Rain Water
 * 
 * Problem Description:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 * 
 * Example 1:
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * 
 * Example 2:
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 * 
 * Approach:
 * Two Pointer Technique O(n):
 * - Use two pointers (left and right) starting from both ends
 * - Track maximum height from left and right sides
 * - For each position, water trapped = min(leftMax, rightMax) - current height
 * - Move the pointer on the side with smaller maximum height
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class TrappingRainWater {
    
    public static int trap(int[] height) {
        if (height == null || height.length < 3) { // Need at least 3 bars to trap water
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;
        
        while (left < right) {
            // Update the maximum height from left side
            leftMax = Math.max(leftMax, height[left]);
            // Update the maximum height from right side
            rightMax = Math.max(rightMax, height[right]);
            
            // If left max is smaller, calculate water for left position and move left
            if (leftMax < rightMax) {
                totalWater += leftMax - height[left];
                left++;
            }
            // If right max is smaller, calculate water for right position and move right
            else {
                totalWater += rightMax - height[right];
                right--;
            }
        }
        
        return totalWater;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, // Standard case with multiple traps
            {4, 2, 0, 3, 2, 5},                     // Case with larger trap in middle
            {7, 0, 7},                               // Simple case with one trap
            {3, 2, 1},                               // No water can be trapped
            {1, 2, 3},                               // No water can be trapped (increasing)
            {1},                                     // Single element
            {}                                       // Empty array
        };
        
        for (int[] height : testCases) {
            System.out.println("\nElevation map: " + arrayToString(height));
            int water = trap(height);
            System.out.println("Trapped water: " + water);
        }
    }
    
    // Helper method to print array
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}