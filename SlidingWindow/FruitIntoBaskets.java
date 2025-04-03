/**
 * Problem: Fruit Into Baskets
 * Approach: Sliding Window with HashMap
 * Time Complexity: O(n)
 * Space Complexity: O(1) since we store at most 3 types
 */
public class FruitIntoBaskets {
    public static int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) return 0;
        
        // HashMap to store fruit type and its last occurrence
        java.util.HashMap<Integer, Integer> basket = new java.util.HashMap<>();
        int maxFruits = 0;
        int left = 0;
        
        // Iterate through the array using right pointer
        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit to basket or update its count
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);
            
            // If we have more than 2 types of fruits
            // remove fruits from left until we have exactly 2 types
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }
            
            // Update maxFruits if current window is larger
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] tests = {
            {1, 2, 1},           // Expected: 3
            {0, 1, 2, 2},        // Expected: 3
            {1, 2, 3, 2, 2},     // Expected: 4
            {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4},  // Expected: 5
            {}                   // Expected: 0
        };
        
        for (int[] test : tests) {
            System.out.println("Input: " + java.util.Arrays.toString(test));
            System.out.println("Maximum fruits: " + totalFruit(test));
            System.out.println();
        }
    }
}