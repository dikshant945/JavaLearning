import java.util.*;

class Movie {
    private String name;
    private int availableSeats;

    public Movie(String name, int totalSeats) {
        this.name = name;
        this.availableSeats = totalSeats;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean bookSeats(int numSeats) {
        if (numSeats > availableSeats) {
            return false;
        }
        availableSeats -= numSeats;
        return true;
    }

    public void cancelSeats(int numSeats) {
        availableSeats += numSeats;
    }
}

class Booking {
    private String movieName;
    private int bookedSeats;

    public Booking(String movieName, int bookedSeats) {
        this.movieName = movieName;
        this.bookedSeats = bookedSeats;
    }

    public String getMovieName() {
        return movieName;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }
}

class MovieTicketBookingSystem {
    private Map<String, Movie> movies;
    private Map<String, List<Booking>> userBookings;

    public MovieTicketBookingSystem() {
        this.movies = new HashMap<>();
        this.userBookings = new HashMap<>();
    }

    // Add a new movie
    public void addMovie(String movieName, int totalSeats) {
        if (movies.containsKey(movieName)) {
            System.out.println("Movie already exists.");
            return;
        }
        movies.put(movieName, new Movie(movieName, totalSeats));
    }

    // Book tickets for a user
    public boolean bookTicket(String movieName, String userName, int numSeats) {
        if (!movies.containsKey(movieName)) {
            System.out.println("Movie not found.");
            return false;
        }

        Movie movie = movies.get(movieName);
        if (!movie.bookSeats(numSeats)) {
            System.out.println("Insufficient seats.");
            return false;
        }

        userBookings.putIfAbsent(userName, new ArrayList<>());
        userBookings.get(userName).add(new Booking(movieName, numSeats));

        return true;
    }

    // Cancel a user's booking
    public boolean cancelBooking(String movieName, String userName) {
        if (!userBookings.containsKey(userName)) {
            return false;
        }

        List<Booking> bookings = userBookings.get(userName);
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getMovieName().equals(movieName)) {
                movies.get(movieName).cancelSeats(booking.getBookedSeats());
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Get available seats for a movie
    public int getAvailableSeats(String movieName) {
        return movies.getOrDefault(movieName, new Movie("", 0)).getAvailableSeats();
    }

    // Get all movies booked by a user
    public List<String> getUserBookings(String userName) {
        if (!userBookings.containsKey(userName)) {
            return Collections.emptyList();
        }
        List<String> movieList = new ArrayList<>();
        for (Booking booking : userBookings.get(userName)) {
            movieList.add(booking.getMovieName());
        }
        return movieList;
    }

    public static void main(String[] args) {
        MovieTicketBookingSystem system = new MovieTicketBookingSystem();

        // Test Case 1: Add a movie
        system.addMovie("Inception", 100);
        System.out.println("Available seats for Inception: " + system.getAvailableSeats("Inception")); // Expected: 100

        // Test Case 2: Book tickets
        boolean booking1 = system.bookTicket("Inception", "Alice", 2);
        System.out.println("Booking status: " + booking1); // Expected: true
        System.out.println("Available seats for Inception: " + system.getAvailableSeats("Inception")); // Expected: 98

        // Test Case 3: Get user bookings
        System.out.println("Alice's bookings: " + system.getUserBookings("Alice")); // Expected: ["Inception"]

        // Test Case 4: Cancel booking
        boolean cancel1 = system.cancelBooking("Inception", "Alice");
        System.out.println("Cancellation status: " + cancel1); // Expected: true
        System.out.println("Available seats for Inception: " + system.getAvailableSeats("Inception")); // Expected: 100
    }
}
