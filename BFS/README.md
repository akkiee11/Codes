# Breadth-First Search (BFS) Pattern

## Introduction
Breadth-First Search is a fundamental graph traversal algorithm that explores all vertices at the present depth before moving to vertices at the next depth level. It's particularly useful for finding shortest paths, level-wise traversal, and solving problems that require exploring neighbors first.

## Core Concepts
1. Uses a queue data structure for processing
2. Visits all nodes at current depth before moving deeper
3. Maintains a visited set to avoid cycles
4. Processes nodes level by level

## Problems by Difficulty

### Easy

1. [**Binary Tree Level Order Traversal**](LevelOrderTraversal.java)
   - Problem Statement: Given a binary tree, return its level order traversal (i.e., from left to right, level by level).
   - Test Cases:
     ```
     Input: [3,9,20,null,null,15,7]
     Output: [[3],[9,20],[15,7]]

     Input: [1]
     Output: [[1]]

     Input: []
     Output: []
     ```
   - Intuition: Use a queue to process nodes level by level. The queue size at each iteration represents the number of nodes at that level.
   - Approach:
     1. Initialize a queue with root node
     2. For each level:
        - Get current level size
        - Process all nodes at current level
        - Add their children to queue
   - Time Complexity: O(n)
   - Space Complexity: O(n)

2. [**Average of Levels**](AverageLevels.java)
   - Problem Statement: Calculate the average value of nodes at each level in a binary tree.
   - Test Cases:
     ```
     Input: [3,9,20,15,7]
     Output: [3.0, 14.5, 11.0]

     Input: [1]
     Output: [1.0]
     ```
   - Intuition: Similar to level order traversal, but maintain sum and count for each level.
   - Approach:
     1. Use BFS with queue
     2. Track sum and count at each level
     3. Calculate average after processing each level

### Medium

1. [**Binary Tree Zigzag Level Order**](ZigzagTraversal.java)
   - Problem Statement: Return the zigzag level order traversal of binary tree nodes (i.e., from left to right, then right to left for the next level).
   - Test Cases:
     ```
     Input: [3,9,20,null,null,15,7]
     Output: [[3],[20,9],[15,7]]

     Input: [1]
     Output: [[1]]
     ```
   - Intuition: Use a flag to track direction and LinkedList for easy insertion at both ends.
   - Approach:
     1. Use standard BFS with queue
     2. Toggle direction flag for each level
     3. Insert nodes at beginning/end based on direction

2. [**Rotting Oranges**](RottingOranges.java)
   - Problem Statement: Find minimum time for all fresh oranges to become rotten in a grid. Each cell contains: 0 (empty), 1 (fresh), or 2 (rotten).
   - Test Cases:
     ```
     Input: [[2,1,1],[1,1,0],[0,1,1]]
     Output: 4

     Input: [[2,1,1],[0,1,1],[1,0,1]]
     Output: -1
     ```
   - Intuition: Multi-source BFS starting from all rotten oranges simultaneously.
   - Approach:
     1. Add all rotten oranges to queue initially
     2. Process level by level, marking fresh oranges as rotten
     3. Track minutes passed and remaining fresh oranges

## Common BFS Patterns
1. Queue-based Implementation
2. Level-wise Processing
3. Distance/Steps Tracking
4. Multiple Source BFS
5. State Space Search

## Tips and Tricks
- Always use a queue for BFS implementation
- Keep track of visited nodes to avoid cycles
- Use level size for level-wise processing
- Consider using multiple queues or markers for complex problems
- Remember to handle edge cases (empty graph, single node, etc.)

## Time/Space Complexity
- Time: O(V + E) for graphs, O(n) for trees
- Space: O(V) for queue and visited set
- Additional space may be needed based on problem requirements