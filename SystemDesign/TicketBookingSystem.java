import java.util.*;

/**
 * Modern System Design Example: Ticket Booking System
 * 
 * This is a low-level design of a movie ticket booking system similar to BookMyShow.
 * The design demonstrates key concepts like:
 * 1. Show Management
 * 2. Seat Selection
 * 3. Concurrent Booking Handling
 * 4. Payment Processing
 * 5. Booking History
 */

public class TicketBookingSystem {
    private ShowService showService;
    private BookingService bookingService;
    private PaymentService paymentService;
    private UserService userService;
    private TheatreService theatreService;
    
    public TicketBookingSystem() {
        this.showService = new ShowService();
        this.bookingService = new BookingService();
        this.paymentService = new PaymentService();
        this.userService = new UserService();
        this.theatreService = new TheatreService();
    }
    
    // Data Models
    static class Show {
        String showId;
        String movieId;
        String theatreId;
        String screenId;
        Date showTime;
        Map<String, Seat> seats; // seatId -> Seat
        double basePrice;
        ShowStatus status;
        
        Show(String movieId, String theatreId, String screenId, Date showTime) {
            this.showId = UUID.randomUUID().toString();
            this.movieId = movieId;
            this.theatreId = theatreId;
            this.screenId = screenId;
            this.showTime = showTime;
            this.seats = new HashMap<>();
            this.status = ShowStatus.OPEN;
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
        String showId;
        List<Seat> seats;
        double totalAmount;
        BookingStatus status;
        Date createdAt;
        PaymentInfo paymentInfo;
        
        Booking(String userId, String showId, List<Seat> seats) {
            this.bookingId = UUID.randomUUID().toString();
            this.userId = userId;
            this.showId = showId;
            this.seats = seats;
            this.status = BookingStatus.INITIATED;
            this.createdAt = new Date();
        }
    }
    
    static class Movie {
        String movieId;
        String title;
        String description;
        int duration; // in minutes
        List<String> cast;
        MovieStatus status;
        Date releaseDate;
    }
    
    static class Theatre {
        String theatreId;
        String name;
        String location;
        List<Screen> screens;
        boolean isActive;
    }
    
    static class Screen {
        String screenId;
        String name;
        int capacity;
        List<Seat> seats;
        boolean isActive;
    }
    
    static class PaymentInfo {
        String paymentId;
        double amount;
        PaymentStatus status;
        PaymentMethod method;
    }
    
    // Enums
    enum ShowStatus {
        OPEN, CLOSED, CANCELLED
    }
    
    enum SeatStatus {
        AVAILABLE, LOCKED, BOOKED
    }
    
    enum SeatType {
        STANDARD, PREMIUM, VIP
    }
    
    enum BookingStatus {
        INITIATED, CONFIRMED, CANCELLED
    }
    
    enum MovieStatus {
        UPCOMING, RELEASED, ARCHIVED
    }
    
    enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
    
    enum PaymentMethod {
        CREDIT_CARD, DEBIT_CARD, NET_BANKING, WALLET
    }
    
    // Service Layer Implementation
    static class ShowService {
        private Map<String, Show> shows;
        private Map<String, List<Show>> movieShows; // movieId -> Shows
        
        ShowService() {
            this.shows = new HashMap<>();
            this.movieShows = new HashMap<>();
        }
        
        public Show createShow(String movieId, String theatreId, 
                             String screenId, Date showTime) {
            Show show = new Show(movieId, theatreId, screenId, showTime);
            shows.put(show.showId, show);
            
            List<Show> movieShowList = movieShows.computeIfAbsent(
                movieId, k -> new ArrayList<>());
            movieShowList.add(show);
            
            return show;
        }
        
        public List<Show> getShowsForMovie(String movieId) {
            return movieShows.getOrDefault(movieId, new ArrayList<>());
        }
        
        public boolean lockSeats(String showId, List<String> seatIds) {
            Show show = shows.get(showId);
            if (show == null) return false;
            
            // Check if all seats are available
            for (String seatId : seatIds) {
                Seat seat = show.seats.get(seatId);
                if (seat == null || seat.status != SeatStatus.AVAILABLE) {
                    return false;
                }
            }
            
            // Lock the seats
            for (String seatId : seatIds) {
                show.seats.get(seatId).status = SeatStatus.LOCKED;
            }
            
            return true;
        }
        
        public void confirmSeats(String showId, List<String> seatIds) {
            Show show = shows.get(showId);
            if (show != null) {
                for (String seatId : seatIds) {
                    Seat seat = show.seats.get(seatId);
                    if (seat != null) {
                        seat.status = SeatStatus.BOOKED;
                    }
                }
            }
        }
        
        public void unlockSeats(String showId, List<String> seatIds) {
            Show show = shows.get(showId);
            if (show != null) {
                for (String seatId : seatIds) {
                    Seat seat = show.seats.get(seatId);
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
        
        public Booking createBooking(String userId, String showId, List<Seat> seats) {
            Booking booking = new Booking(userId, showId, seats);
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
    
    static class TheatreService {
        private Map<String, Theatre> theatres;
        private Map<String, List<Show>> theatreShows; // theatreId -> Shows
        
        TheatreService() {
            this.theatres = new HashMap<>();
            this.theatreShows = new HashMap<>();
        }
        
        public List<Show> getShowsForTheatre(String theatreId) {
            return theatreShows.getOrDefault(theatreId, new ArrayList<>());
        }
    }
    
    // Main workflow
    public Booking bookTickets(String userId, String showId, 
                             List<String> seatIds, PaymentMethod paymentMethod) {
        Show show = showService.shows.get(showId);
        if (show == null) {
            throw new RuntimeException("Show not found");
        }
        
        // Try to lock the seats
        if (!showService.lockSeats(showId, seatIds)) {
            throw new RuntimeException("Selected seats are not available");
        }
        
        try {
            // Create booking with selected seats
            List<Seat> selectedSeats = new ArrayList<>();
            double totalAmount = 0;
            for (String seatId : seatIds) {
                Seat seat = show.seats.get(seatId);
                selectedSeats.add(seat);
                totalAmount += seat.price;
            }
            
            Booking booking = bookingService.createBooking(userId, showId, selectedSeats);
            booking.totalAmount = totalAmount;
            
            // Process payment
            PaymentInfo payment = paymentService.processPayment(booking, paymentMethod);
            booking.paymentInfo = payment;
            
            if (payment.status == PaymentStatus.COMPLETED) {
                // Confirm booking
                bookingService.updateBookingStatus(booking.bookingId, BookingStatus.CONFIRMED);
                showService.confirmSeats(showId, seatIds);
                userService.addBookingToHistory(booking);
            } else {
                // Payment failed, release the seats
                bookingService.updateBookingStatus(booking.bookingId, BookingStatus.CANCELLED);
                showService.unlockSeats(showId, seatIds);
            }
            
            return booking;
            
        } catch (Exception e) {
            // If anything goes wrong, unlock the seats
            showService.unlockSeats(showId, seatIds);
            throw e;
        }
    }
    
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        
        // Example usage
        String userId = "user123";
        String movieId = "movie456";
        String theatreId = "theatre789";
        String screenId = "screen1";
        
        // Create a show
        Show show = bookingSystem.showService.createShow(
            movieId, theatreId, screenId, new Date());
        
        // Add seats to the show
        Seat seat1 = new Seat("A1", SeatType.STANDARD, 10.0);
        Seat seat2 = new Seat("A2", SeatType.STANDARD, 10.0);
        show.seats.put(seat1.seatId, seat1);
        show.seats.put(seat2.seatId, seat2);
        
        // Book tickets
        try {
            List<String> seatIds = Arrays.asList(seat1.seatId, seat2.seatId);
            Booking booking = bookingSystem.bookTickets(
                userId, show.showId, seatIds, PaymentMethod.CREDIT_CARD);
            
            System.out.println("Booking confirmed: " + booking.bookingId);
            System.out.println("Total amount: $" + booking.totalAmount);
            System.out.println("Status: " + booking.status);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}