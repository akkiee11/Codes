/**
 * Accounts Merge
 *
 * Problem: Given a list of accounts where each element accounts[i] is a list of strings, where the first
 * element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is
 * some common email to both accounts. Note that even if two accounts have the same name, they may belong to
 * different people as people could have the same name. A person can have any number of accounts initially,
 * but all of their accounts definitely have the same name.
 *
 * Example 1:
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
 *                   ["John","johnsmith@mail.com","john00@mail.com"],
 *                   ["Mary","mary@mail.com"],
 *                   ["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 *          ["Mary","mary@mail.com"],
 *          ["John","johnnybravo@mail.com"]]
 *
 * Approach:
 * 1. Build a graph where emails are nodes and edges connect emails belonging to same account
 * 2. Use DFS to find connected components (all emails belonging to same person)
 * 3. Sort emails within each component
 * 4. Combine name with sorted emails for final result
 */

import java.util.*;

public class AccountsMerge {
    private Map<String, Set<String>> graph;
    private Set<String> visited;
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Build the graph
        buildGraph(accounts);
        
        // DFS to find connected components
        List<List<String>> mergedAccounts = new ArrayList<>();
        visited = new HashSet<>();
        
        for (List<String> account : accounts) {
            String name = account.get(0);
            String firstEmail = account.get(1);
            
            if (!visited.contains(firstEmail)) {
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(name);
                
                // Find all connected emails
                Set<String> emails = new TreeSet<>(); // TreeSet for automatic sorting
                dfs(firstEmail, emails);
                
                mergedAccount.addAll(emails);
                mergedAccounts.add(mergedAccount);
            }
        }
        
        return mergedAccounts;
    }
    
    private void buildGraph(List<List<String>> accounts) {
        graph = new HashMap<>();
        
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            
            // Add nodes
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                graph.putIfAbsent(email, new HashSet<>());
                
                // Connect first email with all other emails in the account
                if (i > 1) {
                    graph.get(firstEmail).add(email);
                    graph.get(email).add(firstEmail);
                }
            }
        }
    }
    
    private void dfs(String email, Set<String> emails) {
        if (visited.contains(email)) return;
        
        visited.add(email);
        emails.add(email);
        
        for (String neighbor : graph.get(email)) {
            dfs(neighbor, emails);
        }
    }
    
    public static void main(String[] args) {
        AccountsMerge solution = new AccountsMerge();
        
        // Test Case 1
        List<List<String>> accounts1 = Arrays.asList(
            Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
            Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),
            Arrays.asList("Mary","mary@mail.com"),
            Arrays.asList("John","johnnybravo@mail.com")
        );
        
        List<List<String>> result = solution.accountsMerge(accounts1);
        System.out.println("Merged Accounts:");
        for (List<String> account : result) {
            System.out.println(account);
        }
    }
}