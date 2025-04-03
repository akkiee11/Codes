# Depth-First Search (DFS) Pattern

This section covers various implementations of the Depth-First Search algorithm pattern. DFS is a fundamental graph traversal technique that explores as far as possible along each branch before backtracking.

## Key Characteristics of DFS
- Uses a stack (or recursion) for traversal
- Explores deep into a path before backtracking
- Memory efficient for deep graphs
- Natural fit for problems requiring exhaustive search

## Common Applications
1. **Cycle Detection**
   - Finding cycles in directed/undirected graphs
   - Detecting deadlocks in systems
   - Example: Course Schedule problem

2. **Tree Traversals**
   - Pre-order, In-order, Post-order traversals
   - Path finding in trees
   - Tree validation problems

3. **Backtracking**
   - Solving puzzles (N-Queens, Sudoku)
   - Finding all possible combinations/permutations
   - Path finding problems

4. **Connected Components**
   - Finding strongly connected components
   - Island counting problems
   - Network analysis

## Problems by Difficulty

### Easy
- Binary Tree Path Sum
- Symmetric Tree
- Path Sum
- Same Tree

### Medium
- Course Schedule (Cycle Detection)
- Number of Islands
- Clone Graph
- All Paths from Source to Target

### Hard
- Word Search II
- Critical Connections in Network
- Recover Binary Search Tree
- Word Ladder II

## Implementation Patterns

### 1. Basic DFS Template
```java
void dfs(Node node, Set<Node> visited) {
    if (node == null || visited.contains(node)) return;
    
    visited.add(node);
    process(node);
    
    for (Node neighbor : node.neighbors) {
        dfs(neighbor, visited);
    }
}
```

### 2. DFS with Path Tracking
```java
void dfsPath(Node node, Set<Node> visited, List<Node> path) {
    visited.add(node);
    path.add(node);
    
    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            dfsPath(neighbor, visited, path);
        }
    }
    
    path.remove(path.size() - 1); // backtrack
}
```

### 3. DFS for Cycle Detection
```java
boolean hasCycle(Node node, Set<Node> visited, Set<Node> recursionStack) {
    visited.add(node);
    recursionStack.add(node);
    
    for (Node neighbor : node.neighbors) {
        if (!visited.contains(neighbor)) {
            if (hasCycle(neighbor, visited, recursionStack)) {
                return true;
            }
        } else if (recursionStack.contains(neighbor)) {
            return true;
        }
    }
    
    recursionStack.remove(node);
    return false;
}
```

## Time/Space Complexity
- Time: O(V + E) for most graph problems
- Space: O(V) for visited set
- Additional O(H) space for recursion stack, where H is the height of the tree/graph

## Tips and Tricks
1. Always consider base cases carefully
2. Use visited set to avoid cycles in undirected graphs
3. Consider iterative vs recursive implementation based on stack size constraints
4. For backtracking, remember to restore state when backtracking
5. Use recursion stack tracking for directed graph cycle detection