import java.util.*;
import java.security.MessageDigest;

/**
 * Modern System Design Example: URL Shortener Service
 * 
 * This is a low-level design of a URL shortener service similar to bit.ly or tinyurl.
 * The design demonstrates key concepts like:
 * 1. Distributed System Design
 * 2. Data Modeling
 * 3. API Design
 * 4. Scalability Patterns
 * 5. Caching Strategy
 */

public class URLShortener {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int SHORT_URL_LENGTH = 7;
    
    // Simulating distributed cache (In production: Redis/Memcached)
    private Map<String, String> cache;
    
    // Simulating distributed database (In production: Cassandra/DynamoDB)
    private Map<String, URLMapping> database;
    
    // Rate limiter for API protection
    private Map<String, Integer> rateLimiter;
    
    public URLShortener() {
        this.cache = new HashMap<>();
        this.database = new HashMap<>();
        this.rateLimiter = new HashMap<>();
    }
    
    // Data model for URL mapping
    private static class URLMapping {
        String originalURL;
        String shortURL;
        Date createdAt;
        int accessCount;
        
        URLMapping(String originalURL, String shortURL) {
            this.originalURL = originalURL;
            this.shortURL = shortURL;
            this.createdAt = new Date();
            this.accessCount = 0;
        }
    }
    
    /**
     * Generates a short URL for the given long URL
     * Time Complexity: O(1)
     * Space Complexity: O(n) where n is number of URLs
     */
    public String shortenURL(String longURL, String userID) {
        // Rate limiting check
        if (!isRequestAllowed(userID)) {
            throw new RuntimeException("Rate limit exceeded");
        }
        
        // Check cache first
        if (cache.containsKey(longURL)) {
            return cache.get(longURL);
        }
        
        // Generate unique short URL
        String shortURL = generateUniqueShortURL(longURL);
        
        // Store in database
        URLMapping mapping = new URLMapping(longURL, shortURL);
        database.put(shortURL, mapping);
        
        // Update cache
        cache.put(longURL, shortURL);
        cache.put(shortURL, longURL);
        
        return shortURL;
    }
    
    /**
     * Retrieves the original URL from short URL
     * Time Complexity: O(1)
     */
    public String getOriginalURL(String shortURL) {
        // Check cache first
        if (cache.containsKey(shortURL)) {
            return cache.get(shortURL);
        }
        
        // Get from database
        URLMapping mapping = database.get(shortURL);
        if (mapping == null) {
            throw new RuntimeException("URL not found");
        }
        
        // Update analytics
        mapping.accessCount++;
        
        // Update cache
        cache.put(shortURL, mapping.originalURL);
        
        return mapping.originalURL;
    }
    
    /**
     * Generates a unique short URL using base62 encoding
     * In production: Would use distributed ID generator like Twitter's Snowflake
     */
    private String generateUniqueShortURL(String longURL) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(longURL.getBytes());
            
            // Convert first 7 bytes to base62
            StringBuilder shortURL = new StringBuilder();
            for (int i = 0; i < SHORT_URL_LENGTH; i++) {
                shortURL.append(BASE62.charAt(Math.abs(hash[i] % 62)));
            }
            return shortURL.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error generating short URL", e);
        }
    }
    
    /**
     * Simple rate limiting implementation
     * In production: Would use Redis with sliding window
     */
    private boolean isRequestAllowed(String userID) {
        int currentCount = rateLimiter.getOrDefault(userID, 0);
        if (currentCount >= 100) { // 100 requests per minute
            return false;
        }
        rateLimiter.put(userID, currentCount + 1);
        return true;
    }
    
    /**
     * Analytics method to get URL statistics
     */
    public Map<String, Object> getURLStats(String shortURL) {
        URLMapping mapping = database.get(shortURL);
        if (mapping == null) {
            throw new RuntimeException("URL not found");
        }
        
        Map<String, Object> stats = new HashMap<>();
        stats.put("originalURL", mapping.originalURL);
        stats.put("createdAt", mapping.createdAt);
        stats.put("accessCount", mapping.accessCount);
        return stats;
    }
    
    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener();
        
        // Example usage
        String longURL = "https://www.example.com/very/long/url/that/needs/shortening";
        String userID = "user123";
        
        // Create short URL
        String shortURL = urlShortener.shortenURL(longURL, userID);
        System.out.println("Short URL: " + shortURL);
        
        // Get original URL
        String originalURL = urlShortener.getOriginalURL(shortURL);
        System.out.println("Original URL: " + originalURL);
        
        // Get statistics
        Map<String, Object> stats = urlShortener.getURLStats(shortURL);
        System.out.println("URL Statistics: " + stats);
    }
}