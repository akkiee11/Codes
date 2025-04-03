import java.util.*;

/**
 * Task Scheduler
 *
 * Problem Statement:
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter
 * represents a different task. Tasks could be done in any order. Each task is done in one unit
 * of time. For each unit of time, the CPU could complete either one task or just be idle.
 *
 * However, there is a non-negative integer n that represents the cooldown period between two
 * same tasks (the same letter in the array), that is that there must be at least n units of
 * time between any two same tasks.
 *
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 *
 * Constraints:
 * - 1 <= tasks.length <= 10^4
 * - tasks[i] is upper-case English letter
 * - The integer n is in the range [0, 100]
 *
 * Approaches:
 * 1. Priority Queue with Cooldown Queue (Current Implementation)
 *    - Time Complexity: O(T) where T is the total time slots needed
 *    - Space Complexity: O(1) as we store at most 26 characters
 *    - Intuition: Use max heap for highest frequency tasks and queue for cooldown
 *
 * 2. Greedy Approach (Alternative)
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(1)
 *    - Intuition: Calculate idle slots based on max frequency task
 *
 * 3. Mathematical Formula (Alternative)
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(1)
 *    - Intuition: Use formula based on max frequency and count of tasks with max frequency
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        // Count frequency of each task
        int[] frequencies = new int[26];
        for (char task : tasks) {
            frequencies[task - 'A']++;
        }
        
        // Create max heap based on frequencies
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int freq : frequencies) {
            if (freq > 0) {
                maxHeap.offer(freq);
            }
        }
        
        int cycles = 0;
        // Process tasks using a queue to handle cooldown
        Queue<int[]> queue = new LinkedList<>(); // stores [frequency, available_time]
        
        while (!maxHeap.isEmpty() || !queue.isEmpty()) {
            cycles++;
            
            if (!maxHeap.isEmpty()) {
                int freq = maxHeap.poll() - 1;
                if (freq > 0) {
                    queue.offer(new int[]{freq, cycles + n}); // next available time
                }
            }
            
            // Check if any task is available after cooldown
            if (!queue.isEmpty() && queue.peek()[1] == cycles) {
                maxHeap.offer(queue.poll()[0]);
            }
        }
        
        return cycles;
    }
    
    // Test the implementation
    public static void main(String[] args) {
        TaskScheduler solution = new TaskScheduler();
        
        // Test case 1: Regular case
        char[] tasks1 = {'A','A','A','B','B','B'};
        int n1 = 2;
        System.out.println("Test case 1: " + solution.leastInterval(tasks1, n1));
        
        // Test case 2: No cooldown required
        char[] tasks2 = {'A','B','C','D','E','F'};
        int n2 = 0;
        System.out.println("Test case 2: " + solution.leastInterval(tasks2, n2));
        
        // Test case 3: Long cooldown period
        char[] tasks3 = {'A','A','A','A','B','B','B'};
        int n3 = 3;
        System.out.println("Test case 3: " + solution.leastInterval(tasks3, n3));
    }
}