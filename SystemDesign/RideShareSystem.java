import java.util.*;

/**
 * Modern System Design Example: Ride Sharing System
 * 
 * This is a low-level design of a ride sharing service similar to Uber/Lyft.
 * The design demonstrates key concepts like:
 * 1. Real-time Matching Algorithm
 * 2. Dynamic Pricing
 * 3. Location-based Services
 * 4. Payment Processing
 * 5. Driver/Rider Management
 */

public class RideShareSystem {
    private MatchingService matchingService;
    private PricingService pricingService;
    private PaymentService paymentService;
    private UserService userService;
    private LocationService locationService;
    
    public RideShareSystem() {
        this.matchingService = new MatchingService();
        this.pricingService = new PricingService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();
        this.locationService = new LocationService();
    }
    
    // Data Models
    static class Ride {
        String rideId;
        String riderId;
        String driverId;
        Location pickup;
        Location dropoff;
        double estimatedPrice;
        RideStatus status;
        Date createdAt;
        PaymentInfo paymentInfo;
        
        Ride(String riderId, Location pickup, Location dropoff) {
            this.rideId = UUID.randomUUID().toString();
            this.riderId = riderId;
            this.pickup = pickup;
            this.dropoff = dropoff;
            this.status = RideStatus.REQUESTED;
            this.createdAt = new Date();
        }
    }
    
    static class Driver {
        String driverId;
        String name;
        Vehicle vehicle;
        Location currentLocation;
        DriverStatus status;
        double rating;
        List<String> activeRides;
        
        Driver(String name, Vehicle vehicle) {
            this.driverId = UUID.randomUUID().toString();
            this.name = name;
            this.vehicle = vehicle;
            this.status = DriverStatus.OFFLINE;
            this.rating = 5.0;
            this.activeRides = new ArrayList<>();
        }
    }
    
    static class Vehicle {
        String vehicleId;
        String model;
        String plateNumber;
        VehicleType type;
    }
    
    static class Location {
        double latitude;
        double longitude;
        
        public double distanceTo(Location other) {
            // Simplified distance calculation
            return Math.sqrt(Math.pow(latitude - other.latitude, 2) 
                         + Math.pow(longitude - other.longitude, 2));
        }
    }
    
    static class PaymentInfo {
        String paymentId;
        double amount;
        PaymentStatus status;
        PaymentMethod method;
    }
    
    // Enums
    enum RideStatus {
        REQUESTED, DRIVER_ASSIGNED, STARTED, COMPLETED, CANCELLED
    }
    
    enum DriverStatus {
        ONLINE, OFFLINE, ON_TRIP
    }
    
    enum VehicleType {
        ECONOMY, PREMIUM, SUV, LUXURY
    }
    
    enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
    
    enum PaymentMethod {
        CREDIT_CARD, CASH, WALLET
    }
    
    // Service Layer Implementation
    static class MatchingService {
        private Map<String, Driver> availableDrivers;
        private Map<String, Ride> activeRides;
        
        MatchingService() {
            this.availableDrivers = new HashMap<>();
            this.activeRides = new HashMap<>();
        }
        
        public Driver findMatch(Ride ride) {
            // In real system: Use sophisticated matching algorithm
            // considering factors like:
            // 1. Driver proximity
            // 2. Driver rating
            // 3. Vehicle type preference
            // 4. Historical ride data
            
            Driver bestMatch = null;
            double minDistance = Double.MAX_VALUE;
            
            for (Driver driver : availableDrivers.values()) {
                double distance = driver.currentLocation.distanceTo(ride.pickup);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestMatch = driver;
                }
            }
            
            return bestMatch;
        }
        
        public void addAvailableDriver(Driver driver) {
            availableDrivers.put(driver.driverId, driver);
        }
        
        public void removeAvailableDriver(String driverId) {
            availableDrivers.remove(driverId);
        }
    }
    
    static class PricingService {
        private Map<VehicleType, Double> baseRates;
        private Map<String, Double> surgePricing;
        
        PricingService() {
            this.baseRates = new HashMap<>();
            this.surgePricing = new HashMap<>();
            initializeBaseRates();
        }
        
        private void initializeBaseRates() {
            baseRates.put(VehicleType.ECONOMY, 1.0);
            baseRates.put(VehicleType.PREMIUM, 1.5);
            baseRates.put(VehicleType.SUV, 2.0);
            baseRates.put(VehicleType.LUXURY, 3.0);
        }
        
        public double calculatePrice(Location pickup, Location dropoff, VehicleType type) {
            double distance = pickup.distanceTo(dropoff);
            double baseRate = baseRates.get(type);
            double surgeMultiplier = calculateSurgeMultiplier(pickup);
            
            return distance * baseRate * surgeMultiplier;
        }
        
        private double calculateSurgeMultiplier(Location location) {
            // In real system: Consider factors like:
            // 1. Current demand in the area
            // 2. Available drivers
            // 3. Time of day
            // 4. Special events
            return 1.0;
        }
    }
    
    static class PaymentService {
        private Map<String, PaymentInfo> payments;
        
        PaymentService() {
            this.payments = new HashMap<>();
        }
        
        public PaymentInfo processPayment(Ride ride, PaymentMethod method) {
            PaymentInfo payment = new PaymentInfo();
            payment.paymentId = UUID.randomUUID().toString();
            payment.amount = ride.estimatedPrice;
            payment.method = method;
            
            // Simulate payment processing
            payment.status = PaymentStatus.COMPLETED;
            
            payments.put(payment.paymentId, payment);
            return payment;
        }
    }
    
    static class UserService {
        private Map<String, Set<Ride>> userRides;
        
        UserService() {
            this.userRides = new HashMap<>();
        }
        
        public void addRideToHistory(Ride ride) {
            Set<Ride> rides = userRides.computeIfAbsent(
                ride.riderId, k -> new HashSet<>());
            rides.add(ride);
        }
    }
    
    static class LocationService {
        private Map<String, Location> driverLocations;
        
        LocationService() {
            this.driverLocations = new HashMap<>();
        }
        
        public void updateDriverLocation(String driverId, Location location) {
            driverLocations.put(driverId, location);
        }
        
        public Location getDriverLocation(String driverId) {
            return driverLocations.get(driverId);
        }
    }
    
    // Main workflow
    public Ride requestRide(String riderId, Location pickup, Location dropoff, 
                          VehicleType vehicleType) {
        // Create ride request
        Ride ride = new Ride(riderId, pickup, dropoff);
        
        // Calculate estimated price
        double estimatedPrice = pricingService.calculatePrice(pickup, dropoff, vehicleType);
        ride.estimatedPrice = estimatedPrice;
        
        // Find matching driver
        Driver driver = matchingService.findMatch(ride);
        if (driver == null) {
            throw new RuntimeException("No drivers available");
        }
        
        // Assign driver
        ride.driverId = driver.driverId;
        ride.status = RideStatus.DRIVER_ASSIGNED;
        
        // Update driver status
        driver.status = DriverStatus.ON_TRIP;
        driver.activeRides.add(ride.rideId);
        matchingService.removeAvailableDriver(driver.driverId);
        
        // Add to user history
        userService.addRideToHistory(ride);
        
        return ride;
    }
    
    public static void main(String[] args) {
        RideShareSystem rideShare = new RideShareSystem();
        
        // Example usage
        String riderId = "rider123";
        
        // Create locations
        Location pickup = new Location();
        pickup.latitude = 37.7749;
        pickup.longitude = -122.4194;
        
        Location dropoff = new Location();
        dropoff.latitude = 37.7833;
        dropoff.longitude = -122.4167;
        
        // Request ride
        try {
            Ride ride = rideShare.requestRide(riderId, pickup, dropoff, VehicleType.ECONOMY);
            System.out.println("Ride requested: " + ride.rideId);
            System.out.println("Estimated price: $" + ride.estimatedPrice);
            System.out.println("Status: " + ride.status);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}