import java.util.*;

/**
 * Modern System Design Example: Food Delivery System
 * 
 * This is a low-level design of a food delivery system similar to UberEats/DoorDash.
 * The design demonstrates key concepts like:
 * 1. Microservices Architecture
 * 2. Order Management
 * 3. Real-time Location Tracking
 * 4. Payment Processing
 * 5. Restaurant Partner Integration
 */

public class FoodDeliverySystem {
    // Simulating microservices
    private OrderService orderService;
    private RestaurantService restaurantService;
    private DeliveryService deliveryService;
    private PaymentService paymentService;
    private UserService userService;
    
    public FoodDeliverySystem() {
        this.orderService = new OrderService();
        this.restaurantService = new RestaurantService();
        this.deliveryService = new DeliveryService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();
    }
    
    // Data Models
    static class Order {
        String orderId;
        String userId;
        String restaurantId;
        List<MenuItem> items;
        double totalAmount;
        OrderStatus status;
        Date createdAt;
        DeliveryInfo deliveryInfo;
        PaymentInfo paymentInfo;
        
        Order(String userId, String restaurantId, List<MenuItem> items) {
            this.orderId = UUID.randomUUID().toString();
            this.userId = userId;
            this.restaurantId = restaurantId;
            this.items = items;
            this.status = OrderStatus.CREATED;
            this.createdAt = new Date();
        }
    }
    
    static class MenuItem {
        String itemId;
        String name;
        double price;
        int quantity;
    }
    
    static class DeliveryInfo {
        String deliveryId;
        String driverId;
        Location currentLocation;
        Location deliveryLocation;
        DeliveryStatus status;
        Date estimatedDeliveryTime;
    }
    
    static class PaymentInfo {
        String paymentId;
        double amount;
        PaymentStatus status;
        PaymentMethod method;
    }
    
    static class Location {
        double latitude;
        double longitude;
    }
    
    // Enums
    enum OrderStatus {
        CREATED, CONFIRMED, PREPARING, READY_FOR_PICKUP, IN_DELIVERY, DELIVERED, CANCELLED
    }
    
    enum DeliveryStatus {
        ASSIGNED, PICKED_UP, IN_PROGRESS, COMPLETED
    }
    
    enum PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED
    }
    
    enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, WALLET, UPI
    }
    
    // Service Layer Implementation
    static class OrderService {
        private Map<String, Order> orders;
        private Queue<Order> orderQueue;
        
        OrderService() {
            this.orders = new HashMap<>();
            this.orderQueue = new LinkedList<>();
        }
        
        public Order createOrder(String userId, String restaurantId, List<MenuItem> items) {
            Order order = new Order(userId, restaurantId, items);
            orders.put(order.orderId, order);
            orderQueue.offer(order);
            return order;
        }
        
        public Order getOrder(String orderId) {
            return orders.get(orderId);
        }
        
        public void updateOrderStatus(String orderId, OrderStatus status) {
            Order order = orders.get(orderId);
            if (order != null) {
                order.status = status;
            }
        }
    }
    
    static class RestaurantService {
        private Map<String, Set<MenuItem>> menuItems;
        private Map<String, Queue<Order>> restaurantOrders;
        
        RestaurantService() {
            this.menuItems = new HashMap<>();
            this.restaurantOrders = new HashMap<>();
        }
        
        public void acceptOrder(Order order) {
            Queue<Order> orders = restaurantOrders.computeIfAbsent(
                order.restaurantId, k -> new LinkedList<>());
            orders.offer(order);
        }
    }
    
    static class DeliveryService {
        private Map<String, DeliveryInfo> deliveries;
        private Set<String> availableDrivers;
        
        DeliveryService() {
            this.deliveries = new HashMap<>();
            this.availableDrivers = new HashSet<>();
        }
        
        public DeliveryInfo assignDriver(Order order) {
            // In real system: Use location-based driver matching
            String driverId = findNearestDriver(order);
            if (driverId == null) {
                throw new RuntimeException("No drivers available");
            }
            
            DeliveryInfo delivery = new DeliveryInfo();
            delivery.deliveryId = UUID.randomUUID().toString();
            delivery.driverId = driverId;
            delivery.status = DeliveryStatus.ASSIGNED;
            
            deliveries.put(delivery.deliveryId, delivery);
            return delivery;
        }
        
        private String findNearestDriver(Order order) {
            // Simplified driver matching logic
            return availableDrivers.isEmpty() ? null : availableDrivers.iterator().next();
        }
        
        public void updateDeliveryLocation(String deliveryId, Location location) {
            DeliveryInfo delivery = deliveries.get(deliveryId);
            if (delivery != null) {
                delivery.currentLocation = location;
            }
        }
    }
    
    static class PaymentService {
        private Map<String, PaymentInfo> payments;
        
        PaymentService() {
            this.payments = new HashMap<>();
        }
        
        public PaymentInfo processPayment(Order order, PaymentMethod method) {
            PaymentInfo payment = new PaymentInfo();
            payment.paymentId = UUID.randomUUID().toString();
            payment.amount = order.totalAmount;
            payment.method = method;
            
            // Simulate payment processing
            payment.status = PaymentStatus.COMPLETED;
            
            payments.put(payment.paymentId, payment);
            return payment;
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
    public Order placeOrder(String userId, String restaurantId, List<MenuItem> items, 
                          PaymentMethod paymentMethod) {
        // Create order
        Order order = orderService.createOrder(userId, restaurantId, items);
        
        // Process payment
        PaymentInfo payment = paymentService.processPayment(order, paymentMethod);
        order.paymentInfo = payment;
        
        if (payment.status == PaymentStatus.COMPLETED) {
            // Assign to restaurant
            restaurantService.acceptOrder(order);
            orderService.updateOrderStatus(order.orderId, OrderStatus.CONFIRMED);
            
            // Assign delivery partner
            DeliveryInfo delivery = deliveryService.assignDriver(order);
            order.deliveryInfo = delivery;
            
            // Add to user history
            userService.addOrderToHistory(order);
        } else {
            orderService.updateOrderStatus(order.orderId, OrderStatus.CANCELLED);
        }
        
        return order;
    }
    
    public static void main(String[] args) {
        FoodDeliverySystem foodDelivery = new FoodDeliverySystem();
        
        // Example usage
        String userId = "user123";
        String restaurantId = "restaurant456";
        
        // Create order items
        List<MenuItem> items = new ArrayList<>();
        MenuItem item1 = new MenuItem();
        item1.itemId = "item1";
        item1.name = "Pizza";
        item1.price = 15.99;
        item1.quantity = 2;
        items.add(item1);
        
        // Place order
        Order order = foodDelivery.placeOrder(userId, restaurantId, items, PaymentMethod.CREDIT_CARD);
        System.out.println("Order placed: " + order.orderId);
        System.out.println("Order status: " + order.status);
    }
}