import java.util.Stack;

/**
 * Problem: Find the next smaller element to the right for each element in an array.
 * Approach: Use a stack to keep track of potential smaller elements.
 * Intuition: Traverse from the end, maintaining a stack of elements smaller than current.
 * Time Complexity: O(n) - Each element is pushed and popped from the stack at most once.
 * Space Complexity: O(n) - In the worst case, the stack may store all elements.
 */
public class NSR {
        /**
     * Finds the next smaller element to the right for each element in the array
     * @param arr Input array of integers
     * @return Array containing next smaller elements (-1 if none exists)
     */
    public static int[] nextSmallerToRight(int[] arr) {
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
                // Test cases
        int[] arr = {4, 5, 2, 10, 8}; // Basic case
        int[] edgeCase1 = {}; // Empty array
        int[] edgeCase2 = {5, 4, 3, 2, 1}; // Decreasing sequence
        int[] edgeCase3 = {1, 2, 3, 4, 5}; // Increasing sequence

        System.out.println("Basic case:");
        int[] result = nextSmallerToRight(arr);
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}