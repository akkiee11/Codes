import java.util.Stack;

/**
 * NGR (Next Greater Right) implementation using Stack
 * Problem: Given an array, find the next greater element to the right for each element
 * Approach:
 * 1. Use a stack to keep track of potential greater elements
 * 2. Iterate from right to left
 * 3. For each element, pop elements from stack until finding a greater element or stack is empty
 * 4. Push current element to stack
 * Time Complexity: O(n) - Each element is pushed and popped from stack at most once
 * Space Complexity: O(n) - Extra space used by stack
 */
public class NGR {
    public static int[] nextGreaterToRight(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0]; // Handle edge case
        }
        int[] result = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        int[] test1 = {4, 5, 2, 10, 8};
        int[] test2 = {1, 2, 3, 4, 5};
        int[] test3 = {5, 4, 3, 2, 1};
        int[] test4 = {}; // Edge case
        int[] test5 = {2, 2, 2}; // All equal elements

        System.out.println("Test 1 (Expected: 5 10 10 -1 -1):");
        printResult(nextGreaterToRight(test1));

        System.out.println("\nTest 2 (Expected: 2 3 4 5 -1):");
        printResult(nextGreaterToRight(test2));

        System.out.println("\nTest 3 (Expected: -1 -1 -1 -1 -1):");
        printResult(nextGreaterToRight(test3));

        System.out.println("\nTest 4 (Expected: []):");
        printResult(nextGreaterToRight(test4));

        System.out.println("\nTest 5 (Expected: -1 -1 -1):");
        printResult(nextGreaterToRight(test5));
    }

    private static void printResult(int[] arr) {
        int[] arr = {4, 5, 2, 10, 8};
        int[] result = nextGreaterToRight(arr);
        for (int num : result) {
            for (int num : arr) {
            System.out.print(num + " ");
        }
        }
    }
}