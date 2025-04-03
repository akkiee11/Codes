/**
 * Container With Most Water
 * 
 * Problem Description:
 * Given n non-negative integers representing an array of heights where the width of each bar is 1,
 * find two lines that together with the x-axis forms a container that would hold the most water.
 * 
 * Example:
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case,
 * the max area of water (blue section) the container can contain is 49 (between heights 8 and 7).
 * 
 * Approach:
 * Two Pointer Technique (O(n)):
 * - Use two pointers: left and right starting from both ends
 * - Calculate area = min(height[left], height[right]) * (right - left)
 * - Move the pointer pointing to the shorter line inward
 * - Keep track of maximum area seen so far
 * - Time Complexity: O(n), Space Complexity: O(1)
 */

public class ContainerWithMostWater {
    
    public static int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }
        
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Calculate current area
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            int area = width * h;
            
            // Update maximum area if current area is larger
            maxArea = Math.max(maxArea, area);
            
            // Move pointer pointing to shorter line inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 8, 6, 2, 5, 4, 8, 3, 7},  // Standard case
            {1, 1},                        // Minimum case
            {4, 3, 2, 1, 4},              // Equal height at ends
            {1, 2, 4, 3},                 // Small array
            {1, 8, 6, 2, 5, 4, 8, 25, 7}  // Large height difference
        };
        
        for (int[] heights : testCases) {
            System.out.println("\nInput heights: " + arrayToString(heights));
            int result = maxArea(heights);
            System.out.println("Maximum water container area: " + result);
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