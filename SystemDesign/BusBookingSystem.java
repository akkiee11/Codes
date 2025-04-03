import java.util.*;

/**
 * Modern System Design Example: Bus Booking System
 * 
 * This is a low-level design of a bus booking system similar to RedBus.
 * The design demonstrates key concepts like:
 * 1. Route Management
 * 2. Seat Inventory
 * 3. Booking Workflow
 * 4. Payment Processing
 * 5. Booking History
 */

public class BusBookingSystem {
    private RouteService routeService;
    private BusService busService;
    private BookingService bookingService;
    private PaymentService paymentService;
    private UserService userService;
    
    public BusBookingSystem() {
        this.routeService = new RouteService();
        this.busService = new BusService();
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();
    }
    
    // Data Models
    static class Route {
        String routeId;
        String source;
        String destination;
        int distance;
        int duration; // in minutes
        List<Stop> stops;
        RouteStatus status;
        
        Route(String source, String destination) {
            this.routeId = UUID.randomUUID().toString();
            this.source = source;
            this.destination = destination;
            this.stops = new ArrayList<>();
            this.status = RouteStatus.ACTIVE;
        }
    }
    
    static class Stop {
        String stopId;
        String name;
        String city;
        int sequenceNumber;
        int distanceFromSource;
        int timeFromSource; // in minutes
    }
    
    static class Bus {
        String busId;
        String routeId;
        String busNumber;
        BusType type;
        Map<String, Seat> seats; // seatId -> Seat
        BusStatus status;
        Date departureTime;
        Date arrivalTime;
        
        Bus(String routeId, String busNumber, BusType type) {
            this.busId = UUID.randomUUID().toString();
            this.routeId = routeId;
            this.busNumber = busNumber;
            this.type = type;
            this.seats = new HashMap<>();
            this.status = BusStatus.SCHEDULED;
        }
    }
    
    static class Seat {
        String seatId;
        String number;
        SeatType type;
        SeatStatus status;
        double price;
        
        Seat(String number, SeatType type, double price) {
            this.seatId = UUID.randomUUID().toString();
            this.number = number;
            this.type = type;
            this.status = SeatStatus.AVAILABLE;
            this.price = price;
        }
    }
    
    static class Booking {
        String bookingId;
        String userId;
        String busId;
        String source;
        String destination;
        List<Seat> seats;
        double totalAmount;
        BookingStatus status;
        Date travelDate;
        Date createdAt;
        PaymentInfo paymentInfo;
        
        Booking(String userId, String busId, String source, 
                String destination, Date travelDate) {
            this.bookingId = UUID.randomUUID().toString();
            this.userId = userId;
            this.busId = busId;
            this.source = source;
            this.destination = destination;
            this.travelDate = travelDate;
            this.seats = new ArrayList<>();
            this.status = BookingStatus.INITIATED;
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
    enum RouteStatus {
        ACTIVE, INACTIVE
    }
    
    enum BusStatus {
        SCHEDULED, IN_TRANSIT, COMPLETED, CANCELLED
    }
    
    enum SeatStatus {
        AVAILABLE, LOCKED, BOOKED
    }
    
    enum SeatType {
        SEATER, SLEEPER, SEMI_SLEEPER
    }
    
    enum BusType {
        AC, NON_AC, VOLVO, LUXURY
    }
    
    enum BookingStatus {
        INITIATED, CONFIRMED, CANCELLED
    }
    
    enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
    
    enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, NET_BANKING, WALLET
    }
    
    // Service Layer Implementation
    static class RouteService {
        private Map<String, Route> routes;
        private Map<String, List<Bus>> routeBuses; // routeId -> Buses
        
        RouteService() {
            this.routes = new HashMap<>();
            this.routeBuses = new HashMap<>();
        }
        
        public List<Route> searchRoutes(String source, String destination, Date date) {
            List<Route> matchingRoutes = new ArrayList<>();
            for (Route route : routes.values()) {
                if (route.source.equals(source) && 
                    route.destination.equals(destination) &&
                    route.status == RouteStatus.ACTIVE) {
                    matchingRoutes.add(route);
                }
            }
            return matchingRoutes;
        }
        
        public List<Bus> getBusesForRoute(String routeId, Date date) {
            return routeBuses.getOrDefault(routeId, new ArrayList<>());
        }
    }
    
    static class BusService {
        private Map<String, Bus> buses;
        
        BusService() {
            this.buses = new HashMap<>();
        }
        
        public boolean lockSeats(String busId, List<String> seatIds) {
            Bus bus = buses.get(busId);
            if (bus == null) return false;
            
            // Check if all seats are available
            for (String seatId : seatIds) {
                Seat seat = bus.seats.get(seatId);
                if (seat == null || seat.status != SeatStatus.AVAILABLE) {
                    return false;
                }
            }
            
            // Lock the seats
            for (String seatId : seatIds) {
                bus.seats.get(seatId).status = SeatStatus.LOCKED;
            }
            
            return true;
        }
        
        public void confirmSeats(String busId, List<String> seatIds) {
            Bus bus = buses.get(busId);
            if (bus != null) {
                for (String seatId : seatIds) {
                    Seat seat = bus.seats.get(seatId);
                    if (seat != null) {
                        seat.status = SeatStatus.BOOKED;
                    }
                }
            }
        }
        
        public void unlockSeats(String busId, List<String> seatIds) {
            Bus bus = buses.get(busId);
            if (bus != null) {
                for (String seatId : seatIds) {
                    Seat seat = bus.seats.get(seatId);
                    if (seat != null && seat.status == SeatStatus.LOCKED) {
                        seat.status = SeatStatus.AVAILABLE;
                    }
                }
            }
        }
    }
    
    static class BookingService {
        private Map<String, Booking> bookings;
        private Map<String, List<Booking>> userBookings; // userId -> Bookings
        
        BookingService() {
            this.bookings = new HashMap<>();
            this.userBookings = new HashMap<>();
        }
        
        public Booking createBooking(String userId, String busId, 
                                   String source, String destination, 
                                   Date travelDate) {
            Booking booking = new Booking(userId, busId, source, destination, travelDate);
            bookings.put(booking.bookingId, booking);
            
            List<Booking> userBookingList = userBookings.computeIfAbsent(
                userId, k -> new ArrayList<>());
            userBookingList.add(booking);
            
            return booking;
        }
        
        public void updateBookingStatus(String bookingId, BookingStatus status) {
            Booking booking = bookings.get(bookingId);
            if (booking != null) {
                booking.status = status;
            }
        }
    }
    
    static class PaymentService {
        private Map<String, PaymentInfo> payments;
        
        PaymentService() {
            this.payments = new HashMap<>();
        }
        
        public PaymentInfo processPayment(Booking booking, PaymentMethod method) {
            PaymentInfo payment = new PaymentInfo();
            payment.paymentId = UUID.randomUUID().toString();
            payment.amount = booking.totalAmount;
            payment.method = method;
            
            // Simulate payment processing
            payment.status = PaymentStatus.COMPLETED;
            
            payments.put(payment.paymentId, payment);
            return payment;
        }
    }
    
    static class UserService {
        private Map<String, Set<Booking>> userBookingHistory;
        
        UserService() {
            this.userBookingHistory = new HashMap<>();
        }
        
        public void addBookingToHistory(Booking booking) {
            Set<Booking> bookings = userBookingHistory.computeIfAbsent(
                booking.userId, k -> new HashSet<>());
            bookings.add(booking);
        }
    }
    
    // Main workflow
    public Booking bookTickets(String userId, String busId, 
                             String source, String destination,
                             List<String> seatIds, Date travelDate,
                             PaymentMethod paymentMethod) {
        Bus bus = busService.buses.get(busId);
        if (bus == null) {
            throw new RuntimeException("Bus not found");
        }
        
        // Try to lock the seats
        if (!busService.lockSeats(busId, seatIds)) {
            throw new RuntimeException("Selected seats are not available");
        }
        
        try {
            // Create booking with selected seats
            Booking booking = bookingService.createBooking(
                userId, busId, source, destination, travelDate);
            
            double totalAmount = 0;
            for (String seatId : seatIds) {
                Seat seat = bus.seats.get(seatId);
                booking.seats.add(seat);
                totalAmount += seat.price;
            }
            booking.totalAmount = totalAmount;
            
            // Process payment
            PaymentInfo payment = paymentService.processPayment(booking, paymentMethod);
            booking.paymentInfo = payment;
            
            if (payment.status == PaymentStatus.COMPLETED) {
                // Confirm booking
                bookingService.updateBookingStatus(booking.bookingId, BookingStatus.CONFIRMED);
                busService.confirmSeats(busId, seatIds);
                userService.addBookingToHistory(booking);
            } else {
                // Payment failed, release the seats
                bookingService.updateBookingStatus(booking.bookingId, BookingStatus.CANCELLED);
                busService.unlockSeats(busId, seatIds);
            }
            
            return booking;
            
        } catch (Exception e) {
            // If anything goes wrong, unlock the seats
            busService.unlockSeats(busId, seatIds);
            throw e;
        }
    }
    
    public static void main(String[] args) {
        BusBookingSystem busSystem = new BusBookingSystem();
        
        // Example usage
        String userId = "user123";
        String routeId = "route456";
        String source = "New York";
        String destination = "Boston";
        
        // Create a bus
        Bus bus = new Bus(routeId, "BUS001", BusType.AC);
        
        // Add seats to the bus
        Seat seat1 = new Seat("1A", SeatType.SEATER, 25.0);
        Seat seat2 = new Seat("1B", SeatType.SEATER, 25.0);
        bus.seats.put(seat1.seatId, seat1);
        bus.seats.put(seat2.seatId, seat2);
        
        // Set travel date
        Date travelDate = new Date();
        
        // Book tickets
        try {
            List<String> seatIds = Arrays.asList(seat1.seatId, seat2.seatId);
            Booking booking = busSystem.bookTickets(
                userId, bus.busId, source, destination, 
                seatIds, travelDate, PaymentMethod.CREDIT_CARD);
            
            System.out.println("Booking confirmed: " + booking.bookingId);
            System.out.println("Total amount: $" + booking.totalAmount);
            System.out.println("Status: " + booking.status);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}