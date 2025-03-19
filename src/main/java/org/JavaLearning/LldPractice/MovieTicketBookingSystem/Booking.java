package org.JavaLearning.LldPractice.MovieTicketBookingSystem;

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


