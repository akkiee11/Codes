/**
 * Word Search II
 * 
 * Problem: Given an m x n board of characters and a list of strings words, return all words on the board.
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
 * are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example:
 * board = [
 *   ["o","a","a","n"],
 *   ["e","t","a","e"],
 *   ["i","h","k","r"],
 *   ["i","f","l","v"]
 * ]
 * words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 *
 * Approach:
 * 1. Build a Trie with all the words for efficient word lookup
 * 2. Use DFS with backtracking to explore all possible paths on the board
 * 3. Mark visited cells during DFS to avoid reusing them
 * 4. Use Trie to prune paths that won't lead to valid words
 */

import java.util.*;

class WordSearchII {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;
    }
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }
            node.word = word;
        }
        return root;
    }
    
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        TrieNode root = buildTrie(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, result);
            }
        }
        
        return result;
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> result) {
        // Check boundaries and if cell was visited
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || 
            board[i][j] == '#' || node.children[board[i][j] - 'a'] == null) {
            return;
        }
        
        char c = board[i][j];
        node = node.children[c - 'a'];
        
        // Found a word
        if (node.word != null) {
            result.add(node.word);
            node.word = null;  // Mark as visited to avoid duplicates
        }
        
        // Mark current cell as visited
        board[i][j] = '#';
        
        // Explore all 4 directions
        dfs(board, i + 1, j, node, result);
        dfs(board, i - 1, j, node, result);
        dfs(board, i, j + 1, node, result);
        dfs(board, i, j - 1, node, result);
        
        // Restore the cell
        board[i][j] = c;
    }

    // Time Complexity: O(M * N * 4^L) where M,N are dimensions of board and L is max length of words
    // Space Complexity: O(K) where K is total number of letters in dictionary
    public static void main(String[] args) {
        WordSearchII solution = new WordSearchII();
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        
        List<String> result = solution.findWords(board, words);
        System.out.println("Found words: " + result);
    }
}