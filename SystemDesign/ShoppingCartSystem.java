import java.util.*;

/**
 * Modern System Design Example: Shopping Cart System
 * 
 * This is a low-level design of a shopping cart system similar to Amazon.
 * The design demonstrates key concepts like:
 * 1. Cart Management
 * 2. Inventory Tracking
 * 3. Price Calculation
 * 4. Cart Persistence
 * 5. Order Processing
 */

public class ShoppingCartSystem {
    private CartService cartService;
    private InventoryService inventoryService;
    private PricingService pricingService;
    private OrderService orderService;
    private UserService userService;
    
    public ShoppingCartSystem() {
        this.cartService = new CartService();
        this.inventoryService = new InventoryService();
        this.pricingService = new PricingService();
        this.orderService = new OrderService();
        this.userService = new UserService();
    }
    
    // Data Models
    static class Cart {
        String cartId;
        String userId;
        List<CartItem> items;
        double totalAmount;
        CartStatus status;
        Date lastUpdated;
        
        Cart(String userId) {
            this.cartId = UUID.randomUUID().toString();
            this.userId = userId;
            this.items = new ArrayList<>();
            this.status = CartStatus.ACTIVE;
            this.lastUpdated = new Date();
        }
    }
    
    static class CartItem {
        String productId;
        String name;
        int quantity;
        double price;
        boolean inStock;
        
        CartItem(String productId, String name, int quantity, double price) {
            this.productId = productId;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
            this.inStock = true;
        }
    }
    
    static class Product {
        String productId;
        String name;
        double price;
        int stockQuantity;
        ProductCategory category;
        boolean isAvailable;
        
        Product(String name, double price, int stockQuantity, ProductCategory category) {
            this.productId = UUID.randomUUID().toString();
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
            this.category = category;
            this.isAvailable = stockQuantity > 0;
        }
    }
    
    static class Order {
        String orderId;
        String userId;
        String cartId;
        List<CartItem> items;
        double totalAmount;
        OrderStatus status;
        Date createdAt;
        PaymentInfo paymentInfo;
        
        Order(String userId, String cartId, List<CartItem> items) {
            this.orderId = UUID.randomUUID().toString();
            this.userId = userId;
            this.cartId = cartId;
            this.items = items;
            this.status = OrderStatus.CREATED;
            this.createdAt = new Date();
        }
    }
    
    static class PaymentInfo {
        String paymentId;
        double amount;
        PaymentStatus status;
        PaymentMethod method;
    }
    
    // Enums
    enum CartStatus {
        ACTIVE, CHECKED_OUT, ABANDONED
    }
    
    enum OrderStatus {
        CREATED, CONFIRMED, PROCESSING, SHIPPED, DELIVERED, CANCELLED
    }
    
    enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
    
    enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, NET_BANKING, WALLET
    }
    
    enum ProductCategory {
        ELECTRONICS, CLOTHING, BOOKS, HOME, GROCERY
    }
    
    // Service Layer Implementation
    static class CartService {
        private Map<String, Cart> carts;
        private Map<String, Cart> userCarts; // userId -> Cart mapping
        
        CartService() {
            this.carts = new HashMap<>();
            this.userCarts = new HashMap<>();
        }
        
        public Cart createCart(String userId) {
            Cart cart = new Cart(userId);
            carts.put(cart.cartId, cart);
            userCarts.put(userId, cart);
            return cart;
        }
        
        public void addItem(String cartId, CartItem item) {
            Cart cart = carts.get(cartId);
            if (cart != null) {
                cart.items.add(item);
                cart.lastUpdated = new Date();
            }
        }
        
        public void removeItem(String cartId, String productId) {
            Cart cart = carts.get(cartId);
            if (cart != null) {
                cart.items.removeIf(item -> item.productId.equals(productId));
                cart.lastUpdated = new Date();
            }
        }
        
        public void updateItemQuantity(String cartId, String productId, int quantity) {
            Cart cart = carts.get(cartId);
            if (cart != null) {
                for (CartItem item : cart.items) {
                    if (item.productId.equals(productId)) {
                        item.quantity = quantity;
                        cart.lastUpdated = new Date();
                        break;
                    }
                }
            }
        }
    }
    
    static class InventoryService {
        private Map<String, Product> products;
        
        InventoryService() {
            this.products = new HashMap<>();
        }
        
        public boolean checkAvailability(String productId, int quantity) {
            Product product = products.get(productId);
            return product != null && product.stockQuantity >= quantity;
        }
        
        public void updateStock(String productId, int quantity) {
            Product product = products.get(productId);
            if (product != null) {
                product.stockQuantity = quantity;
                product.isAvailable = quantity > 0;
            }
        }
    }
    
    static class PricingService {
        private Map<String, Double> productPrices;
        private Map<String, Double> discounts; // productId -> discount percentage
        
        PricingService() {
            this.productPrices = new HashMap<>();
            this.discounts = new HashMap<>();
        }
        
        public double calculateTotal(Cart cart) {
            double total = 0;
            for (CartItem item : cart.items) {
                double itemPrice = item.price * item.quantity;
                Double discount = discounts.get(item.productId);
                if (discount != null) {
                    itemPrice *= (1 - discount);
                }
                total += itemPrice;
            }
            return total;
        }
    }
    
    static class OrderService {
        private Map<String, Order> orders;
        
        OrderService() {
            this.orders = new HashMap<>();
        }
        
        public Order createOrder(Cart cart) {
            Order order = new Order(cart.userId, cart.cartId, cart.items);
            orders.put(order.orderId, order);
            return order;
        }
        
        public void updateOrderStatus(String orderId, OrderStatus status) {
            Order order = orders.get(orderId);
            if (order != null) {
                order.status = status;
            }
        }
    }
    
    static class UserService {
        private Map<String, Set<Order>> userOrders;
        
        UserService() {
            this.userOrders = new HashMap<>();
        }
        
        public void addOrderToHistory(Order order) {
            Set<Order> orders = userOrders.computeIfAbsent(
                order.userId, k -> new HashSet<>());
            orders.add(order);
        }
    }
    
    // Main workflow
    public Order checkout(String cartId, PaymentMethod paymentMethod) {
        Cart cart = cartService.carts.get(cartId);
        if (cart == null || cart.items.isEmpty()) {
            throw new RuntimeException("Cart is empty or not found");
        }
        
        // Verify inventory
        for (CartItem item : cart.items) {
            if (!inventoryService.checkAvailability(item.productId, item.quantity)) {
                throw new RuntimeException("Product " + item.name + " is out of stock");
            }
        }
        
        // Calculate final price
        double totalAmount = pricingService.calculateTotal(cart);
        
        // Create order
        Order order = orderService.createOrder(cart);
        order.totalAmount = totalAmount;
        
        // Process payment (simplified)
        PaymentInfo payment = new PaymentInfo();
        payment.paymentId = UUID.randomUUID().toString();
        payment.amount = totalAmount;
        payment.method = paymentMethod;
        payment.status = PaymentStatus.COMPLETED;
        order.paymentInfo = payment;
        
        if (payment.status == PaymentStatus.COMPLETED) {
            // Update inventory
            for (CartItem item : cart.items) {
                Product product = inventoryService.products.get(item.productId);
                if (product != null) {
                    inventoryService.updateStock(item.productId, 
                        product.stockQuantity - item.quantity);
                }
            }
            
            // Update order status
            orderService.updateOrderStatus(order.orderId, OrderStatus.CONFIRMED);
            
            // Add to user history
            userService.addOrderToHistory(order);
            
            // Clear cart
            cart.status = CartStatus.CHECKED_OUT;
            cart.items.clear();
        } else {
            orderService.updateOrderStatus(order.orderId, OrderStatus.CANCELLED);
        }
        
        return order;
    }
    
    public static void main(String[] args) {
        ShoppingCartSystem cartSystem = new ShoppingCartSystem();
        
        // Example usage
        String userId = "user123";
        
        // Create a cart
        Cart cart = cartSystem.cartService.createCart(userId);
        
        // Add items to cart
        CartItem item1 = new CartItem("prod1", "Laptop", 1, 999.99);
        CartItem item2 = new CartItem("prod2", "Mouse", 2, 29.99);
        cartSystem.cartService.addItem(cart.cartId, item1);
        cartSystem.cartService.addItem(cart.cartId, item2);
        
        // Checkout
        try {
            Order order = cartSystem.checkout(cart.cartId, PaymentMethod.CREDIT_CARD);
            System.out.println("Order placed: " + order.orderId);
            System.out.println("Total amount: $" + order.totalAmount);
            System.out.println("Status: " + order.status);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}