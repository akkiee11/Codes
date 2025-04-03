import java.util.Stack;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * 
 * Approach:
 * 1. Use a stack to keep track of indices of bars in increasing order of height.
 * 2. Iterate through the heights array:
 *    - If the current height is greater than the height at the top of the stack, push the index onto the stack.
 *    - Otherwise, calculate the area of the rectangle with the height at the top of the stack and update maxArea.
 * 3. After the iteration, process the remaining elements in the stack.
 * 
 * Time Complexity: O(n) - Each element is pushed and popped from the stack exactly once.
 * Space Complexity: O(n) - The stack can grow up to size n in the worst case.
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, heights[top] * width);
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxArea = Math.max(maxArea, heights[top] * width);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();
        int[] heights = {2,1,5,6,2,3};
        System.out.println("Largest Rectangle Area: " + solution.largestRectangleArea(heights)); // Output: 10
        
        // Additional test cases
        int[] test1 = {2,4}; // Expected: 4
        int[] test2 = {2,1,2}; // Expected: 3
        int[] test3 = {0,9}; // Expected: 9
        int[] test4 = {6,7,5,2,4,5,9,3}; // Expected: 16
        
        System.out.println("Test 1: " + solution.largestRectangleArea(test1));
        System.out.println("Test 2: " + solution.largestRectangleArea(test2));
        System.out.println("Test 3: " + solution.largestRectangleArea(test3));
        System.out.println("Test 4: " + solution.largestRectangleArea(test4));
    }
}