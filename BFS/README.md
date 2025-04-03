# Breadth-First Search (BFS) Pattern

## Introduction
Breadth-First Search is a fundamental graph traversal algorithm that explores all vertices at the present depth before moving to vertices at the next depth level. It's particularly useful for finding shortest paths, level-wise traversal, and solving problems that require exploring nodes in layers.

## Problems by Difficulty

### Easy
1. [**Binary Tree Level Order Traversal**](LevelOrderTraversal.java)
   - Problem: Print level-wise nodes of a binary tree
   - Learning: Basic BFS implementation with queue

2. [**Average of Levels**](AverageLevels.java)
   - Problem: Find average of all values at each tree level
   - Learning: Level-wise traversal with value calculation

3. [**Symmetric Tree**](SymmetricTree.java)
   - Problem: Check if binary tree is symmetric around center
   - Learning: Level-wise comparison of nodes

### Medium
1. [**Binary Tree Zigzag Level Order**](ZigzagTraversal.java)
   - Problem: Print tree levels in alternating order
   - Learning: BFS with direction tracking

2. [**Shortest Path in Binary Matrix**](ShortestPathMatrix.java)
   - Problem: Find shortest path from top-left to bottom-right
   - Learning: BFS in matrix with distance tracking

3. [**Rotting Oranges**](RottingOranges.java)
   - Problem: Time for all oranges to rot in grid
   - Learning: Multi-source BFS with time tracking

### Hard
1. [**Word Ladder**](WordLadder.java)
   - Problem: Find shortest transformation sequence
   - Learning: BFS with string manipulation

2. [**Bus Routes**](BusRoutes.java)
   - Problem: Least number of buses to reach destination
   - Learning: BFS with multiple graphs

3. [**Sliding Puzzle**](SlidingPuzzle.java)
   - Problem: Minimum moves to solve sliding puzzle
   - Learning: BFS with state tracking

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