import java.util.Stack;
import java.util.EmptyStackException;

/**
 * MinStack implementation that supports push, pop, top, and getMin operations in O(1) time.
 * Uses two stacks: one for storing elements and another for tracking minimum values.
 *
 * Approach:
 * - Maintain two stacks: 'stack' for regular operations and 'minStack' for tracking minimums
 * - When pushing a value, also push it to minStack if it's <= current minimum
 * - When popping, remove from minStack only if the value equals current minimum
 *
 * Time Complexity:
 * - All operations (push, pop, top, getMin) are O(1)
 *
 * Space Complexity:
 * - O(n) in worst case where all elements are in decreasing order
 */

public class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        // Edge case: Handle null values if needed
        if (stack == null || minStack == null) {
            throw new IllegalStateException("Stacks not initialized");
        }
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    public void pop() {
        // Edge case: Handle empty stack
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        if (stack.isEmpty()) return;
        int val = stack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }

    public int top() {
        // Edge case: Handle empty stack
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.peek();
    }

    public int getMin() {
        // Edge case: Handle empty stack
        if (minStack.isEmpty()) {
            throw new EmptyStackException();
        }
        return minStack.peek();
    }

    public static void main(String[] args) {
        // Test cases
        MinStack minStack = new MinStack();

        // Test 1: Basic operations
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Min: " + minStack.getMin()); // -3
        minStack.pop();
        System.out.println("Top: " + minStack.top()); // 0
        System.out.println("Min: " + minStack.getMin()); // -2

        // Test 2: Empty stack
        try {
            new MinStack().pop();
            assert false; // Should not reach here
        } catch (EmptyStackException e) {
            // Expected
        }

        // Test 3: Duplicate minimum values
        MinStack minStack2 = new MinStack();
        minStack2.push(5);
        minStack2.push(5);
        minStack2.pop();
        System.out.println("Min: " + minStack2.getMin()); // 5
        MinStack minStack3 = new MinStack();
        minStack3.push(-2);
        minStack3.push(0);
        minStack3.push(-3);
        System.out.println("Min: " + minStack.getMin()); // -3
        minStack.pop();
        System.out.println("Top: " + minStack.top()); // 0
        System.out.println("Min: " + minStack.getMin()); // -2
    }
}