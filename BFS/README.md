# Breadth-First Search (BFS) Pattern

## Introduction
Breadth-First Search is a fundamental graph traversal algorithm that explores all vertices at the present depth before moving to vertices at the next depth level. It's particularly useful for finding shortest paths, level-wise traversal, and solving problems that require exploring nodes in layers.

The key intuition behind BFS is that it guarantees the shortest path in unweighted graphs by exploring nodes in order of their distance from the source. This makes it ideal for problems involving shortest paths, level-wise processing, or finding the minimum number of steps to reach a target.

## Problems by Difficulty
### Easy

1. [**Binary Tree Level Order Traversal**](LevelOrderTraversal.java)
   - Problem: Print level-wise nodes of a binary tree
   - Learning: Basic BFS implementation with queue
   - Explanation: This problem involves traversing a binary tree level by level, which is a classic application of BFS. By using a queue, we can efficiently process each level of the tree, ensuring that nodes are visited in the correct order.
   - Time Complexity: O(n), where n is the number of nodes in the tree.
   - Space Complexity: O(n), due to the space required for the queue.

2. [**Average of Levels**](AverageLevels.java)
   - Problem: Find average of all values at each tree level
   - Learning: Level-wise traversal with value calculation
   - Explanation: This problem extends the basic BFS traversal by calculating the average value of nodes at each level. By maintaining a running sum and count of nodes at each level, we can compute the average efficiently.
   - Time Complexity: O(n), where n is the number of nodes in the tree.
   - Space Complexity: O(n), due to the space required for the queue.

3. [**Symmetric Tree**](SymmetricTree.java)
   - Problem: Check if binary tree is symmetric around center
   - Learning: Level-wise comparison of nodes
   - Explanation: This problem uses BFS to compare nodes at each level to determine if the tree is symmetric. By comparing nodes in pairs, we can efficiently check for symmetry.
   - Time Complexity: O(n), where n is the number of nodes in the tree.
   - Space Complexity: O(n), due to the space required for the queue.

### Hard

1. [**Word Ladder**](WordLadder.java)
   - Problem: Find shortest transformation sequence
   - Learning: BFS with string manipulation
   - Explanation: This problem involves finding the shortest transformation sequence from a start word to an end word, using a dictionary of valid words. BFS is used to explore all possible transformations level by level, ensuring the shortest path is found.
   - Time Complexity: O(n * m^2), where n is the number of words in the dictionary and m is the length of each word.
   - Space Complexity: O(n * m), due to the space required for the queue and visited set.

2. [**Bus Routes**](BusRoutes.java)
   - Problem: Least number of buses to reach destination
   - Learning: BFS with multiple graphs
   - Explanation: This problem models bus routes as a graph, where each bus stop is a node and each bus route is an edge. BFS is used to find the shortest path from the start stop to the destination stop, minimizing the number of buses taken.
   - Time Complexity: O(n^2), where n is the number of bus stops.
   - Space Complexity: O(n), due to the space required for the queue and visited set.

3. [**Sliding Puzzle**](SlidingPuzzle.java)
   - Problem: Minimum moves to solve sliding puzzle
   - Learning: BFS with state tracking
   - Explanation: This problem involves solving a sliding puzzle by finding the minimum number of moves required to reach the goal state. BFS is used to explore all possible moves from the current state, ensuring the shortest path to the goal is found.
   - Time Complexity: O(n!), where n is the number of tiles in the puzzle.
   - Space Complexity: O(n!), due to the space required for the queue and visited set.

### Medium
1. [**Clone Graph**](CloneGraph.java)
   - Problem: Clone a graph
   - Learning: BFS traversal for graph cloning

2. [**Course Schedule**](CourseSchedule.java)
   - Problem: Determine if you can finish all courses
   - Learning: BFS for cycle detection in directed graph

3. [**Number of Islands**](NumberOfIslands.java)
   - Problem: Count number of islands in a grid
   - Learning: BFS for connected component counting

4. [**Rotting Oranges**](RottingOranges.java)
   - Problem: Minimum time to rot all oranges
   - Learning: BFS for multi-source shortest path

5. [**Walls and Gates**](WallsAndGates.java)
   - Problem: Fill each empty room with distance to nearest gate
   - Learning: BFS for distance calculation in grid

6. [**Zigzag Traversal**](ZigzagTraversal.java)
   - Problem: Zigzag level order traversal of binary tree
   - Learning: BFS with alternating level processing

2. [**Binary Tree Zigzag Level Order**](ZigzagTraversal.java)
   - Problem: Print tree levels in alternating order
   - Learning: BFS with direction tracking

3. [**Rotting Oranges**](RottingOranges.java)
   - Problem: Time for all oranges to rot in grid
   - Learning: Multi-source BFS with time tracking

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