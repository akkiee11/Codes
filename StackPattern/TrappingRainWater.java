import java.util.Stack;

/**
 * Problem: Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * Approach: Using a stack to keep track of bars. When we find a bar longer than the bar at the top of the stack,
 * we calculate the trapped water between them. The stack stores indices of bars in decreasing order of height.
 *
 * Time Complexity: O(n) - Each bar is pushed and popped from the stack exactly once
 * Space Complexity: O(n) - Worst case when bars are in decreasing order
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        int i = 0;
        
        while (i < height.length) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                water += distance * boundedHeight;
            }
            stack.push(i++);
        }
        return water;
    }

    
    // Test cases
    public static void main(String[] args) {
        // Basic test case
        System.out.println("Test Case 1:");
        int[] height1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Expected: 6, Actual: " + new TrappingRainWater().trap(height1));

        // Edge case: Empty array
        System.out.println("\nTest Case 2:");
        int[] height2 = {};
        System.out.println("Expected: 0, Actual: " + new TrappingRainWater().trap(height2));

        // Edge case: All bars same height
        System.out.println("\nTest Case 3:");
        int[] height3 = {5,5,5,5};
        System.out.println("Expected: 0, Actual: " + new TrappingRainWater().trap(height3));

        // Edge case: Ascending bars
        System.out.println("\nTest Case 4:");
        int[] height4 = {1,2,3,4,5};
        System.out.println("Expected: 0, Actual: " + new TrappingRainWater().trap(height4));

        // Edge case: Descending bars
        System.out.println("\nTest Case 5:");
        int[] height5 = {5,4,3,2,1};
        System.out.println("Expected: 0, Actual: " + new TrappingRainWater().trap(height5));
    }
        TrappingRainWater solution = new TrappingRainWater();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Trapped Water: " + solution.trap(height)); // Output: 6
    }
}